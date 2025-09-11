package atm.infrastructure.output.adapters.persistence

import atm.core.application.ports.output.BanknoteBalancePort
import atm.core.application.ports.output.BanknoteLoaderPort
import atm.core.application.ports.output.BanknoteWithdrawPort
import atm.core.domain.Banknote
import java.util.*

class InMemoryBanknoteStorage :
    BanknoteLoaderPort,
    BanknoteWithdrawPort,
    BanknoteBalancePort {
    /**
     * Хранилище банкнот в банкомате (Banknote storage in an ATM)
     */
    private val storage = EnumMap<Banknote, Int>(Banknote::class.java)

    override fun load(banknotes: Map<Banknote, Int>) {
        banknotes.forEach { (banknote, amount) ->
            storage[banknote] = storage.getOrPut(banknote) { 0 } + amount
        }
    }

    override fun withdraw(banknotes: Map<Banknote, Int>) {
        banknotes.forEach { (banknote, amount) ->
            val noteInStorage = storage.getValue(banknote)
            if (noteInStorage > 0 && amount <= noteInStorage) {
                storage[banknote] = noteInStorage - amount
            }
        }
    }

    override fun getAll() = storage
}
