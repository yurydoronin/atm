package atm.api.adapter.`in`.web

import atm.core.application.port.`in`.BalanceResult
import atm.core.application.port.`in`.GetBalanceUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/atm")
class GetBalanceController(
    private val getBalanceService: GetBalanceUseCase
) {
    @GetMapping("/balance")
    fun balance(): BalanceResult = getBalanceService.getBalance()
}
