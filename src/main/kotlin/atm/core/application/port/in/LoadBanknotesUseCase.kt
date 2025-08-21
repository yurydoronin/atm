package atm.core.application.port.`in`

import atm.core.domain.Banknote

interface LoadBanknotesUseCase {
    /**
     * Загрузить купюры в банкомат (Load banknotes into an ATM)
     */
    fun load(command: LoadBanknotesCommand): LoadBanknotesResult
}

/**
 * (DTO) Команда на загрузку купюр в банкомат
 */
data class LoadBanknotesCommand(
    val banknotes: Map<Banknote, Int>
)

/**
 * (DTO) Сумма закруженных купюр в банкомат (Total amount in an ATM)
 */
data class LoadBanknotesResult(val message: String)