package atm.core.domain

/**
 * Купюра с номиналом (Banknote with denomination)
 */
enum class Banknote(val nominal: Int) {
    B5000(5000),
    B2000(2000),
    B1000(1000),
    B500(500),
    B200(200),
    B100(100),
    B50(50),
}