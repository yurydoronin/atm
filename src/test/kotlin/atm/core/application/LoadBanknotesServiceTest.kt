package atm.core.application

import atm.core.application.port.`in`.LoadBanknotesCommand
import atm.core.application.port.out.BanknoteLoaderPort
import atm.core.domain.Banknote
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class LoadBanknotesServiceTest {
    // relaxed = true позволяет не писать every { loader.load(any()) } just runs явно
    private val loader = mockk<BanknoteLoaderPort>(relaxed = true)

    private val service by lazy { LoadBanknotesService(loader) }

    @Test
    fun `should load banknotes and return correct result`() {
        val command = LoadBanknotesCommand(
            banknotes = mapOf(
                Banknote.B100 to 3,
                Banknote.B50 to 4
            )
        )

        val result = service.load(command)

        assertEquals("Loaded 500 RUB", result.message)
        verify { loader.load(command.banknotes) }
    }
}
