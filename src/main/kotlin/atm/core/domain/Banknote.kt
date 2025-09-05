package atm.core.domain

/**
 * Banknote with denomination
 */
enum class Banknote(val nominal: Int) {
    B5000(5000),
    B2000(2000),
    B1000(1000),
    B500(500),
    B200(200),
    B100(100),
    B50(50);

    companion object {
        /**
         * Returns the Banknote corresponding to the given numeric denomination.
         *
         * @param nominalValue the numeric denomination of the banknote
         * @throws IllegalArgumentException if no banknote exists with the given denomination
         */
        fun fromNominal(nominalValue: String): Banknote {
            val value = nominalValue.toIntOrNull()
                ?: throw IllegalArgumentException("Invalid nominal format: $nominalValue")
            return entries.find { it.nominal == value }
                ?: throw IllegalArgumentException("No banknote with nominal $value")
        }
    }
}