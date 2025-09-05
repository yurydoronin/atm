package atm.core.application

import atm.core.application.port.`in`.BalanceResult
import atm.core.application.port.`in`.GetBalanceUseCase
import atm.core.application.port.out.BanknoteBalancePort

internal class GetBalanceService(private val balance: BanknoteBalancePort) : GetBalanceUseCase {

    override fun getBalance(): BalanceResult {
        val total = balance.getAll().entries.sumOf { it.key.nominal * it.value }

        return BalanceResult(totalBalance = "Current balance: $total RUB")
    }
}