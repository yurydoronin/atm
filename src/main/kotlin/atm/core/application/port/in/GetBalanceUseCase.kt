package atm.core.application.port.`in`

import atm.core.application.dto.results.BalanceResult

interface GetBalanceUseCase {

    /**
     * Запросить баланс (Request balance)
     */
    fun getBalance(): BalanceResult
}