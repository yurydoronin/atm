package atm.core.application

import atm.core.application.ports.output.BanknoteBalancePort
import atm.core.domain.Banknote
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GetBalanceServiceTest {

    private val balancePort = mockk<BanknoteBalancePort>()

    private val service by lazy { GetBalanceService(balancePort) }

    @Test
    fun `should return correct balance`() {
        every { balancePort.getAll() } returns mapOf(Banknote.B100 to 5)

        val result = service.getBalance()

        assertEquals("Current balance: 500 RUB", result.totalBalance)
    }
}
