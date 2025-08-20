package atm.core.application.dto.results

import atm.core.domain.Banknote

data class WithdrawMoneyResult(
    val message: String,
    // Информация о том, какие банкноты и в каком количестве банкомат выдал
    val dispensed: Map<Banknote, Int> = emptyMap()
)
