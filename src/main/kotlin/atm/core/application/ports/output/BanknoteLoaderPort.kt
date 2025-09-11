package atm.core.application.ports.output

import atm.core.domain.Banknote

interface BanknoteLoaderPort {
    /**
     * Загрузка купюр в банкомат
     */
    fun load(banknotes: Map<Banknote, Int>)
}