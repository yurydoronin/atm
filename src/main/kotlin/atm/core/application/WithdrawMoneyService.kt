package atm.core.application

import atm.core.application.port.`in`.WithdrawMoneyCommand
import atm.core.application.port.`in`.WithdrawMoneyResult
import atm.core.application.port.`in`.WithdrawMoneyUseCase
import atm.core.application.port.out.BanknoteBalancePort
import atm.core.application.port.out.BanknoteWithdrawPort
import atm.core.domain.Banknote
import atm.core.domain.Money
import java.util.*

internal class WithdrawMoneyService(
    private val withdraw: BanknoteWithdrawPort,
    private val balance: BanknoteBalancePort,
) : WithdrawMoneyUseCase {

    // Используется жадный алгоритм (A greedy algorithm is used)
    // В реальном банкомате скорее всего используется динамическое программирование (DP)
    override fun withdraw(command: WithdrawMoneyCommand): WithdrawMoneyResult {
        // Валидация входных данных
        if (command.money.amount <= 0) {
            return WithdrawMoneyResult(message = "Невозможно снять сумму 0 или меньше")
        }

        val moneyToWithdraw = Money(command.money.amount)

        // Проверяем достаточно ли средств
        if (totalBalance() < moneyToWithdraw) {
            return WithdrawMoneyResult(message = "В банкомате недостаточно средств")
        }

        /*
        Сортируем банкноты по номиналу (по убыванию).
        Сортировка по убыванию позволяет сначала использовать банкноты с наибольшим номиналом.
        Это гарантирует, что сумма будет выдана минимальным количеством банкнот.
        */
        val sortedBanknotes = Banknote.entries.sortedByDescending { it.nominal }

        // Проверяем, можем ли снять нужную сумму
        var remaining = moneyToWithdraw
        val dispensed = EnumMap<Banknote, Int>(Banknote::class.java)

        // Перебирает все возможные номиналы банкнот, чтобы вычислить, сколько купюр каждого номинала нужно выдать,
        // исходя из запрашиваемой суммы (remainingAmount) и количества купюр в хранилище (storage).
        for (banknote in sortedBanknotes) {
            // Количество конкретной банкноты в хранилище
            val available = balance.getAll().getOrDefault(banknote, 0)
            // Запрашиваемая сумма (remainingAmount) должна быть больше или равна номиналу банкноты.
            // И в хранилище должно быть хотя бы одно количество этой банкноты.
            val nominal = Money(banknote.nominal)

            if (remaining >= nominal && available > 0) {
                val notesToWithdraw = minOf(remaining.amount / nominal.amount, available)
                if (notesToWithdraw > 0) {
                    dispensed[banknote] = notesToWithdraw
                    remaining -= nominal * notesToWithdraw
                }
            }
        }

        // Если после прохода по всем номиналам remaining всё еще больше нуля,
        // это означает, что запрошенную сумму выдать невозможно (не хватает купюр).
        if (remaining.amount > 0) {
            return WithdrawMoneyResult(message = "Невозможно снять запрашиваемую сумму ${command.money.amount} RUB")
        }

        // Снимаем купюры из банкомата
        withdraw.withdraw(dispensed)

        return WithdrawMoneyResult(
            message = "Выдано ${command.money.amount} RUB",
            dispensed = dispensed
        )
    }

    /**
     * Вспомогательный метод. Остаток в банкомате (наминал * на количество купюр)
     *
     * Amount of cash in an ATM (denomination * number of banknotes)
     */
    private fun totalBalance(): Money =
        Money(balance.getAll().entries.sumOf { it.key.nominal * it.value })

}