package atm.core.application.port.`in`

import atm.core.application.dto.commands.LoadBanknotesCommand
import atm.core.application.dto.results.LoadBanknotesResult

interface LoadBanknotesUseCase {

    /**
     * Загрузить купюры в банкомат (Load banknotes into an ATM)
     *
     * @param command команда на загрузку купюр в банкомат
     * @return сумма в банкомате (Total amount in an ATM)
     */
    fun load(command: LoadBanknotesCommand): LoadBanknotesResult
}