package atm.core.domain

/**
 * Strong typing (value-object)
 */
@JvmInline
value class Money(val amount: Int) {

    init {
        require(amount >= 0) { "The amount cannot be negative" }
    }

    operator fun plus(other: Money) = Money(amount + other.amount)
    operator fun minus(other: Money) = Money(amount - other.amount)
    operator fun times(multiplier: Int) = Money(amount * multiplier)
    operator fun compareTo(other: Money): Int = amount.compareTo(other.amount)

}
