package com.backend.webshop.model

data class ShoppingCartResponse(
    val customerId: String,
    val orderPositions: List<OrderPositionResponse>,
    val totalCostInCent: Long,
    val deliveryCostInCent: Long,
    val deliveryOption: String
)
