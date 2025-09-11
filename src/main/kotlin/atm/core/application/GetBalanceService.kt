package atm.core.application

import atm.core.application.ports.input.BalanceResult
import atm.core.application.ports.input.GetBalanceUseCase
import atm.core.application.ports.output.BanknoteBalancePort

internal class GetBalanceService(private val balance: BanknoteBalancePort) : GetBalanceUseCase {

    override fun getBalance(): BalanceResult {
        val total = balance.getAll().entries.sumOf { it.key.nominal * it.value }

        return BalanceResult(totalBalance = "Current balance: $total RUB")
    }
}