package atm.core.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class AmountOfMoneyTest {

    @Test
    fun `should create AmountOfMoney with non-negative value`() {
        val money = AmountOfMoney(100)

        assertEquals(100, money.amount)
    }

    @Test
    fun `should throw exception for negative value`() {
        val exception = assertThrows<IllegalArgumentException> {
            AmountOfMoney(-1)
        }

        assertEquals("Сумма не может быть отрицательной", exception.message)
    }

    @Test
    fun `plus should sum amounts correctly`() {
        val a = AmountOfMoney(100)
        val b = AmountOfMoney(50)

        val result = a + b

        assertEquals(150, result.amount)
    }

    @Test
    fun `minus should subtract amounts correctly`() {
        val a = AmountOfMoney(100)
        val b = AmountOfMoney(30)

        val result = a - b

        assertEquals(70, result.amount)
    }

    @Test
    fun `times should multiply amount correctly`() {
        val a = AmountOfMoney(50)

        val result = a * 3

        assertEquals(150, result.amount)
    }

    @Test
    fun `compareTo should work correctly`() {
        val a = AmountOfMoney(100)
        val b = AmountOfMoney(50)

        assertTrue(a > b)
        assertTrue(b < a)
        assertEquals(0, a.compareTo(AmountOfMoney(100)))
    }
}
