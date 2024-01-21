package com.backend.webshop.Exceptions

import org.springframework.http.HttpStatus

data class WebshopException(
    override val message: String,
    val statusCode: HttpStatus
): Exception(message)

data class IdNotFoundException(
    override val message: String,
    val statusCode: HttpStatus
): Exception(message)