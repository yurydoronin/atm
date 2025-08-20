package atm.core.application

import atm.core.application.dto.commands.LoadBanknotesCommand
import atm.core.application.dto.results.LoadBanknotesResult
import atm.core.application.port.`in`.LoadBanknotesUseCase
import atm.core.application.port.out.BanknoteLoaderPort
import atm.core.domain.Banknote

internal class LoadBanknotesService(private val loader: BanknoteLoaderPort) : LoadBanknotesUseCase {

    override fun load(command: LoadBanknotesCommand): LoadBanknotesResult {
        val banknotes = command.banknotes
        loader.add(banknotes)

        return LoadBanknotesResult(
            message = "Загружено ${total(banknotes)} RUB"
        )
    }

    /**
     * Вспомогательный метод. Загруженная сума денег в банкомат.
     */
    private fun total(banknotes: Map<Banknote, Int>): Int =
        banknotes.entries.sumOf { it.key.nominal * it.value }

}