package atm.core.application.port.`in`

import atm.core.domain.Banknote

interface LoadBanknotesUseCase {
    /**
     * Load banknotes into an ATM
     */
    fun load(command: LoadBanknotesCommand): LoadBanknotesResult
}

/**
 * (DTO) Command to load banknotes into an ATM
 */
data class LoadBanknotesCommand(val banknotes: Map<Banknote, Int>)

/**
 * (DTO) Total amount in an ATM
 */
@JvmInline
value class LoadBanknotesResult(val message: String)