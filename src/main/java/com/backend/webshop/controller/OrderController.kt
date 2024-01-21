package com.backend.webshop.controller

import com.backend.webshop.model.CreateOrderRequest
import com.backend.webshop.model.CreateOrderPositionRequest
import com.backend.webshop.model.OrderResponse
import com.backend.webshop.service.OrderService
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderController(
    val orderService: OrderService
) {
    @PostMapping("/orders")
    fun createOrder(
        @RequestBody request: CreateOrderRequest
    ): OrderResponse {
            return orderService.createOrder(request)
    }

    @PostMapping("/orders/{id}/positions")
    fun createOrderPosition(
        @PathVariable(name = "id") orderId: String,
        @RequestBody request: CreateOrderPositionRequest
    ) {
        orderService.createNewOrderPosition(orderId, request)
    }
}

