package atm.core.application.port.out

import atm.core.domain.Banknote

interface BanknoteWithdrawPort {

    /**
     * Снятие купюр с банкомата
     */
    fun remove(banknotes: Map<Banknote, Int>)
}