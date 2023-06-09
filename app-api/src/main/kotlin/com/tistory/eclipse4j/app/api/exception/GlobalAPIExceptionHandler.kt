package com.tistory.eclipse4j.app.api.exception

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.context.request.WebRequest


@ControllerAdvice
class GlobalAPIExceptionHandler {
    @ExceptionHandler(IllegalStateException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleEmployeeNotFound(
        exception: IllegalStateException
    ): ResponseEntity<String?>? {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .header("content-type", "application/json;charset=UTF-8")
            .body(exception.message)
    }
}