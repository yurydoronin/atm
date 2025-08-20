package atm.api.adapter.`in`.web

import atm.core.application.dto.commands.LoadBanknotesCommand
import atm.core.application.dto.commands.WithdrawMoneyCommand
import atm.core.application.dto.results.BalanceResult
import atm.core.application.dto.results.LoadBanknotesResult
import atm.core.application.dto.results.WithdrawMoneyResult
import atm.core.application.port.`in`.GetBalanceUseCase
import atm.core.application.port.`in`.LoadBanknotesUseCase
import atm.core.application.port.`in`.WithdrawMoneyUseCase
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/atm")
class AtmController(
    private val loadBanknotesService: LoadBanknotesUseCase,
    private val withdrawMoneyService: WithdrawMoneyUseCase,
    private val getBalanceService: GetBalanceUseCase
) {
    @PostMapping("/load")
    fun load(@RequestBody command: LoadBanknotesCommand): LoadBanknotesResult =
        loadBanknotesService.load(command)

    @PostMapping("/withdraw")
    fun withdraw(@RequestBody command: WithdrawMoneyCommand): WithdrawMoneyResult =
        withdrawMoneyService.withdraw(command)

    @GetMapping("/balance")
    fun balance(): BalanceResult =
        getBalanceService.getBalance()
}
