package atm.infrastructure.adapter.out.persistence

import atm.core.application.port.out.BanknoteBalancePort
import atm.core.application.port.out.BanknoteLoaderPort
import atm.core.application.port.out.BanknoteWithdrawPort
import atm.core.domain.Banknote
import java.util.*

class InMemoryBanknoteStorage :
    BanknoteLoaderPort,
    BanknoteWithdrawPort,
    BanknoteBalancePort {
    /**
     * Хранилище банкнот в банкомате (Banknote storage in an ATM)
     */
    private val storage = EnumMap<Banknote, Int>(Banknote::class.java).withDefault { 0 }

    override fun load(banknotes: Map<Banknote, Int>) {
        banknotes.forEach { (banknote, amount) ->
            storage[banknote] = storage.getValue(banknote) + amount
        }
    }

    override fun withdraw(banknotes: Map<Banknote, Int>) {
        banknotes.forEach { (banknote, amount) ->
            storage[banknote] = storage.getValue(banknote) - amount
        }
    }

    override fun getAll() = storage
}
