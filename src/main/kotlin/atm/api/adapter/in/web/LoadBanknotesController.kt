package atm.api.adapter.`in`.web

import atm.core.application.port.`in`.LoadBanknotesCommand
import atm.core.application.port.`in`.LoadBanknotesResult
import atm.core.application.port.`in`.LoadBanknotesUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/atm")
class LoadBanknotesController(
    private val loadBanknotesService: LoadBanknotesUseCase
) {
    @PostMapping("/load")
    fun load(@RequestBody command: LoadBanknotesCommand): LoadBanknotesResult =
        loadBanknotesService.load(command)
}
