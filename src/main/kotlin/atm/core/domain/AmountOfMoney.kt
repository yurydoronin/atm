package atm.core.domain

/**
 * Value-object
 */
@JvmInline
value class AmountOfMoney(val amount: Int) {

    init {
        require(amount >= 0) { "Сумма не может быть отрицательной" }
    }

    operator fun plus(other: AmountOfMoney) = AmountOfMoney(this.amount + other.amount)
    operator fun minus(other: AmountOfMoney) = AmountOfMoney(this.amount - other.amount)
    operator fun times(multiplier: Int) = AmountOfMoney(this.amount * multiplier)
    operator fun compareTo(other: AmountOfMoney): Int = this.amount.compareTo(other.amount)

}



