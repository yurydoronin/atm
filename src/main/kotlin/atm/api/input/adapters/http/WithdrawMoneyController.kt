package atm.api.input.adapters.http

import atm.core.application.ports.input.WithdrawMoneyCommand
import atm.core.application.ports.input.WithdrawMoneyResult
import atm.core.application.ports.input.WithdrawMoneyUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.collections.component1
import kotlin.collections.component2
import kotlin.collections.mapKeys

@RestController
@RequestMapping("/atm")
class WithdrawMoneyController(
    private val withdrawMoneyUseCase: WithdrawMoneyUseCase

) {
    @PostMapping("/withdraw")
    fun withdraw(@RequestBody request: WithdrawMoneyRequest): WithdrawMoneyResponse =
        withdrawMoneyUseCase.withdraw(request.toCommand()).toResponse()
}

/**
 * (DTO) HTTP-Request to withdraw money from an ATM
 */
data class WithdrawMoneyRequest(val amountOfMoney: Int)

/**
 * (DTO) HTTP-Response with ATM withdrawal details
 */
data class WithdrawMoneyResponse(
    val message: String,
    val dispensed: Map<String, Int> = emptyMap(),
)

/**
 * An extension function for mapping a request to a use case command
 */
fun WithdrawMoneyRequest.toCommand(): WithdrawMoneyCommand =
    WithdrawMoneyCommand(amountOfMoney)

/**
 * An extension function for mapping use case result to response DTO
 */
fun WithdrawMoneyResult.toResponse(): WithdrawMoneyResponse =
    WithdrawMoneyResponse(
        message,
        dispensed = dispensed.mapKeys { (note, _) -> note.nominal.toString() },
    )
