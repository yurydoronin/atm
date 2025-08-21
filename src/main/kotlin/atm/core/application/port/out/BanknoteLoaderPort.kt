package atm.core.application.port.out

import atm.core.domain.Banknote

interface BanknoteLoaderPort {
    /**
     * Загрузка купюр в банкомат
     */
    fun load(banknotes: Map<Banknote, Int>)
}