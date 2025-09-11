package atm.core.application

import atm.core.application.ports.input.WithdrawMoneyCommand
import atm.core.application.ports.input.WithdrawMoneyResult
import atm.core.application.ports.input.WithdrawMoneyUseCase
import atm.core.application.ports.output.BanknoteBalancePort
import atm.core.application.ports.output.BanknoteWithdrawPort
import atm.core.domain.Banknote
import atm.core.domain.Money
import java.util.*

internal class WithdrawMoneyService(
    private val withdraw: BanknoteWithdrawPort,
    private val balance: BanknoteBalancePort,
) : WithdrawMoneyUseCase {

    // Используется жадный алгоритм (Using a greedy algorithm)
    // В реальном банкомате скорее всего используется динамическое программирование (DP)
    override fun withdraw(command: WithdrawMoneyCommand): WithdrawMoneyResult {
        val moneyToWithdraw = command.money

        // Валидация входных данных
        if (moneyToWithdraw.amount == 0) {
            return WithdrawMoneyResult(message = "Unable to withdraw amount 0")
        }
        // Проверяем достаточно ли средств
        if (totalBalance() < moneyToWithdraw) {
            return WithdrawMoneyResult(message = "There are not enough funds in the ATM")
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
        // исходя из запрашиваемой суммы (remaining) и количества купюр в хранилище (storage).
        for (banknote in sortedBanknotes) {
            // Количество конкретной банкноты в хранилище
            val available = balance.getAll().getOrDefault(banknote, 0)
            val nominal = Money(banknote.nominal)
            // Запрашиваемая сумма (remaining) должна быть больше или равна номиналу банкноты.
            // И в хранилище должно быть хотя бы одно количество этой банкноты.
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
            return WithdrawMoneyResult(message = "Unable to withdraw requested amount ${moneyToWithdraw.amount} RUB")
        }

        // Снимаем купюры из банкомата
        withdraw.withdraw(dispensed)

        return WithdrawMoneyResult(
            message = "Dispensed ${moneyToWithdraw.amount} RUB",
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