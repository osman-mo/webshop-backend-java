package com.backend.webshop.repositories

import com.backend.webshop.Exceptions.IdNotFoundException
import com.backend.webshop.model.*
import com.backend.webshop.persistence.*
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.UUID

@Repository
@Transactional
class OrderRepository(
    private val orders: OrderJpaRepository,
    private val customers: CustomerJpaRepository
) {
    fun save(request: CreateOrderRequest): OrderResponse {
        val customer = customers.findById(request.customerId)
            .orElseThrow { IdNotFoundException("Customer with id ${request.customerId} not found") }
        return orders.save(OrderEntity(UUID.randomUUID().toString(), customer,
            LocalDateTime.now(), OrderStatus.NEW)).toResponse()
    }

    @Transactional(readOnly = true)
    fun findById(orderId: String): OrderResponse? = orders.findById(orderId).orElse(null)?.toResponse()

    @Transactional(readOnly = true)
    fun findAllNewOrdersByCustomerId(customerId: String): List<OrderResponse> =
        orders.findAllByCustomerIdAndStatus(customerId, OrderStatus.NEW).map { it.toResponse() }
}
