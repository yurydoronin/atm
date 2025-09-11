package atm.api.input.adapters.http

import atm.core.application.ports.input.BalanceResult
import atm.core.application.ports.input.GetBalanceUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/atm")
class GetBalanceController(
    private val getBalanceUseCase: GetBalanceUseCase
) {
    @GetMapping("/balance")
    fun balance(): BalanceResult = getBalanceUseCase.getBalance()
}
