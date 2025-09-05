package atm.core.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class AmountOfMoneyTest {

    @Test
    fun `should create Money with non-negative value`() {
        val money = Money(100)

        assertEquals(100, money.amount)
    }

    @Test
    fun `should throw exception for negative value`() {
        val exception = assertThrows<IllegalArgumentException> {
            Money(-1)
        }

        assertEquals("The amount cannot be negative", exception.message)
    }
}
