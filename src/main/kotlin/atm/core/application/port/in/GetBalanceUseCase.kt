package atm.core.application.port.`in`

interface GetBalanceUseCase {
    /**
     * Запросить баланс (Request balance)
     */
    fun getBalance(): BalanceResult
}

/**
 * (DTO) Сумма (остаток) в банкомате (Total amount in an ATM)
 */
data class BalanceResult(val totalBalance: String)