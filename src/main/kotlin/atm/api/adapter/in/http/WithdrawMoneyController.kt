package atm.api.adapter.`in`.http

import atm.core.application.port.`in`.WithdrawMoneyCommand
import atm.core.application.port.`in`.WithdrawMoneyResult
import atm.core.application.port.`in`.WithdrawMoneyUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/atm")
class WithdrawMoneyController(
    private val withdrawMoneyService: WithdrawMoneyUseCase

) {
    @PostMapping("/withdraw")
    fun withdraw(@RequestBody command: WithdrawMoneyCommand): WithdrawMoneyResult =
        withdrawMoneyService.withdraw(command)
}
