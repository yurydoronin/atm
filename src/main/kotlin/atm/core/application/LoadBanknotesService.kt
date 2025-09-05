package atm.core.application

import atm.core.application.port.`in`.LoadBanknotesCommand
import atm.core.application.port.`in`.LoadBanknotesResult
import atm.core.application.port.`in`.LoadBanknotesUseCase
import atm.core.application.port.out.BanknoteLoaderPort
import atm.core.domain.Banknote

internal class LoadBanknotesService(private val loader: BanknoteLoaderPort) : LoadBanknotesUseCase {

    override fun load(command: LoadBanknotesCommand): LoadBanknotesResult {
        val banknotes = command.banknotes
        loader.load(banknotes)

        return LoadBanknotesResult(
            message = "Loaded ${total(banknotes)} RUB"
        )
    }

    /**
     * Вспомогательный метод. Загруженная сума денег в банкомат.
     */
    private fun total(banknotes: Map<Banknote, Int>): Int =
        banknotes.entries.sumOf { it.key.nominal * it.value }

}