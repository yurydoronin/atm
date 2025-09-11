package atm.core.application.ports.output

import atm.core.domain.Banknote

interface BanknoteWithdrawPort {
    /**
     * Снятие купюр с банкомата
     */
    fun withdraw(banknotes: Map<Banknote, Int>)
}