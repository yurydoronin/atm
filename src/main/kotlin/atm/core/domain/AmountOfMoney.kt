package atm.core.domain

/**
 * Value-object
 */
@JvmInline
value class Money(val amount: Int) {

    init {
        require(amount >= 0) { "Сумма не может быть отрицательной" }
    }

    operator fun plus(other: Money) = Money(amount + other.amount)
    operator fun minus(other: Money) = Money(amount - other.amount)
    operator fun times(multiplier: Int) = Money(amount * multiplier)
    operator fun compareTo(other: Money): Int = amount.compareTo(other.amount)

}
