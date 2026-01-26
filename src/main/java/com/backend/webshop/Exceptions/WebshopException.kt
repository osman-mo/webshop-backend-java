package com.backend.webshop.Exceptions

import org.springframework.http.HttpStatus

data class EstoreException(
    override val message: String,
    val statusCode: HttpStatus
): RuntimeException(message)

data class IdNotFoundException (
    override val message: String,
    val statusCode: HttpStatus = HttpStatus.BAD_REQUEST
): RuntimeException(message)