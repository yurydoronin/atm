package atm.core.application

import atm.core.application.ports.input.WithdrawMoneyCommand
import atm.core.application.ports.output.BanknoteBalancePort
import atm.core.application.ports.output.BanknoteWithdrawPort
import atm.core.domain.Banknote
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class WithdrawMoneyServiceTest {

    private val withdrawPort = mockk<BanknoteWithdrawPort>(relaxed = true)
    private val balancePort = mockk<BanknoteBalancePort>()

    private val service by lazy { WithdrawMoneyService(withdrawPort, balancePort) }

    @Test
    fun `should return error when amount is zero or negative`() {
        val result = service.withdraw(WithdrawMoneyCommand(0))

        assertEquals("Unable to withdraw amount 0", result.message)
    }

    @Test
    fun `should return error when balance is insufficient`() {
        every { balancePort.getAll() } returns mapOf(Banknote.B100 to 1)

        val result = service.withdraw(WithdrawMoneyCommand(500))

        assertEquals("There are not enough funds in the ATM", result.message)
    }

    @Test
    fun `should return error when not enough banknotes for exact amount`() {
        every { balancePort.getAll() } returns mapOf(
            Banknote.B100 to 1,
            Banknote.B50 to 1
        )

        val result = service.withdraw(WithdrawMoneyCommand(70))

        assertEquals("Unable to withdraw requested amount 70 RUB", result.message)
    }

    @Test
    fun `should successfully withdraw money`() {
        every { balancePort.getAll() } returns mapOf(
            Banknote.B100 to 3,
            Banknote.B50 to 4
        )

        val result = service.withdraw(WithdrawMoneyCommand(250))

        assertEquals("Dispensed 250 RUB", result.message)
        assertEquals(mapOf(Banknote.B100 to 2, Banknote.B50 to 1), result.dispensed)

        verify { withdrawPort.withdraw(result.dispensed) }
    }
}
