package atm.api.adapter.`in`.web

import atm.core.application.port.`in`.*
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
