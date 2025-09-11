package atm.core.application.ports.output

import atm.core.domain.Banknote

interface BanknoteBalancePort {
    /**
     * Запрос состояния хранилища банкомата
     */
    fun getAll(): Map<Banknote, Int>
}