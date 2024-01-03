package com.backend.webshop.model

import java.time.LocalDateTime

data class OrderResponse (
        val id: String,
        val customerId: String,
        val orderTime: LocalDateTime,
        val status: OrderStatus,
        val orderPositionResponses: List<OrderPositionResponse>
)

enum class OrderStatus {
    NEW, CONFIRMED, SENT, DELIVERED, CANCELLED
}

data class OrderPositionResponse(
        val id: String,
        val productId: String,
        val quantity: Long,
        )