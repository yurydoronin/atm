package atm.api.adapter.`in`.http

import atm.core.application.port.`in`.LoadBanknotesCommand
import atm.core.application.port.`in`.LoadBanknotesResult
import atm.core.application.port.`in`.LoadBanknotesUseCase
import atm.core.domain.Banknote
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/atm")
class LoadBanknotesController(
    private val loadBanknotesUseCase: LoadBanknotesUseCase
) {
    @PostMapping("/load")
    fun load(@RequestBody request: LoadBanknotesRequest): LoadBanknotesResponse =
        loadBanknotesUseCase.load(request.toCommand()).toResponse()
}

/**
 * (DTO) HTTP-Request to load banknotes into ATM
 */
data class LoadBanknotesRequest(val banknotes: Map<String, Int>)

/**
 * (DTO) HTTP-Response containing the amount of notes loaded into the ATM
 */
data class LoadBanknotesResponse(val message: String)

/**
 * An extension function for mapping a request to a use case command
 */
fun LoadBanknotesRequest.toCommand(): LoadBanknotesCommand =
    LoadBanknotesCommand(
        banknotes = banknotes.mapKeys { (nominal, _) -> Banknote.valueOf(nominal) }
    )

/**
 * An extension function for mapping use case result to response DTO
 */
fun LoadBanknotesResult.toResponse(): LoadBanknotesResponse =
    LoadBanknotesResponse(message)
