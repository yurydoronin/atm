package atm.core.application.port.`in`

import atm.core.domain.Banknote
import atm.core.domain.Money

interface WithdrawMoneyUseCase {
    /**
     * Withdraw the required amount of money from an ATM
     */
    fun withdraw(command: WithdrawMoneyCommand): WithdrawMoneyResult
}

/**
 * (DTO) Command to withdraw money from an ATM
 */
data class WithdrawMoneyCommand(val amountOfMoney: Int) {
    val money: Money by lazy { Money(amountOfMoney) }
}

/**
 * (DTO) An ATM withdrawal operation result
 *
 * @property dispensed Information about what banknotes and in what quantities the ATM issued
 */
data class WithdrawMoneyResult(
    val message: String,
    val dispensed: Map<Banknote, Int> = emptyMap(),
)