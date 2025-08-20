package atm.core.application.dto.commands

import atm.core.domain.Banknote

data class LoadBanknotesCommand(
    val banknotes: Map<Banknote, Int>
)