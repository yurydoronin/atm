package atm.core.application.port.`in`

import atm.core.domain.Banknote
import atm.core.domain.Money

interface WithdrawMoneyUseCase {
    /**
     * Снять нужную сумму денег в банкомате (Withdraw the required amount of money from an ATM)
     */
    fun withdraw(command: WithdrawMoneyCommand): WithdrawMoneyResult
}

/**
 * (DTO) Команда на снятие денег с банкомата
 */
data class WithdrawMoneyCommand(val amountOfMoney: Int) {
    val money: Money by lazy { Money(amountOfMoney) }
}

/**
 * (DTO) Результат операции снятия денег с банкомата
 *
 * @property dispensed Информация о том, какие банкноты и в каком количестве банкомат выдал
 */
data class WithdrawMoneyResult(
    val message: String,
    val dispensed: Map<Banknote, Int> = emptyMap()
)