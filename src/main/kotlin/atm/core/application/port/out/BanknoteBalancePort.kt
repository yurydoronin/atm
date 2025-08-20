package atm.core.application.port.out

import atm.core.domain.Banknote

interface BanknoteBalancePort {

    /**
     * Запрос состояния хранилища банкомата
     */
    fun getAll(): Map<Banknote, Int>
}