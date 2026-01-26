package com.backend.webshop.repositories

import com.backend.webshop.model.CreateOrderRequest
import com.backend.webshop.model.OrderResponse
import com.backend.webshop.model.OrderStatus
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class OrderRepository(
    private val orders: MutableList<OrderResponse>
) {


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

    fun findAllNewOrdersByCustomerId(customerId: String): List<OrderResponse> {
        return orders.filter{it.customerId == customerId && it.status == OrderStatus.NEW}
    }
}
