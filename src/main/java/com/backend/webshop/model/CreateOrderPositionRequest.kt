package com.backend.webshop.model

data class CreateOrderPositionRequest(
        val productId: String,
        val quantity: Long
) {

}
