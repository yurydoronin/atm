package atm.core.application.ports.input

interface GetBalanceUseCase {
    /**
     * Запросить баланс (Request balance)
     */
    fun getBalance(): BalanceResult
}

/**
 * (DTO) Сумма (остаток) в банкомате (Total amount in an ATM)
 */
@JvmInline
value class BalanceResult(val totalBalance: String)