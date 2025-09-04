package atm.api.adapter.`in`.http

import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgument(ex: IllegalArgumentException): LoadBanknotesResponse =
        LoadBanknotesResponse(message = ex.message ?: "Unknown error")
}