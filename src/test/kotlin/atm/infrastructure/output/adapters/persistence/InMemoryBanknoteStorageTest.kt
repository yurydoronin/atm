package atm.infrastructure.output.adapters.persistence

import atm.core.domain.Banknote
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class InMemoryBanknoteStorageTest {

    private val storage by lazy { InMemoryBanknoteStorage() }

    @Test
    fun `should load banknotes correctly`() {
        storage.load(mapOf(Banknote.B100 to 2))

        assertEquals(2, storage.getAll()[Banknote.B100])
    }

    @Test
    fun `should withdraw banknotes correctly`() {
        storage.load(mapOf(Banknote.B100 to 5))
        storage.withdraw(mapOf(Banknote.B100 to 3))

        assertEquals(2, storage.getAll()[Banknote.B100])
    }

    @Test
    fun `getAll should return current state`() {
        storage.load(mapOf(Banknote.B50 to 4))

        assertEquals(4, storage.getAll()[Banknote.B50])
    }
}
