package atm.core.application.port.`in`

import atm.core.application.dto.commands.WithdrawMoneyCommand
import atm.core.application.dto.results.WithdrawMoneyResult

interface WithdrawMoneyUseCase {

    /**
     * Снять нужную сумму денег в банкомате (Withdraw the required amount of money from an ATM)
     *
     * @param command команда на снятие денег с банкомата
     * @return сумма в банкомате (Total amount in an ATM)
     */
    fun withdraw(command: WithdrawMoneyCommand): WithdrawMoneyResult
}