package com.backend.webshop.repositories

import com.backend.webshop.model.CreateOrderRequest
import com.backend.webshop.model.OrderResponse
import com.backend.webshop.model.OrderStatus
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.util.*

@Repository
class OrderRepository(private val orders: MutableList<OrderResponse>) {


    fun save(request: CreateOrderRequest): OrderResponse {
        val orderResponse = OrderResponse(
                id = UUID.randomUUID().toString(),
                customerId = request.customerId,
                orderTime = LocalDateTime.now(),
                status = OrderStatus.NEW,
                orderPositionResponses =  emptyList()
        )

        orders.add(orderResponse)
        return orderResponse
    }

    fun findById(orderId: String): OrderResponse? {
        return orders.find{ it.id == orderId}
    }
}
