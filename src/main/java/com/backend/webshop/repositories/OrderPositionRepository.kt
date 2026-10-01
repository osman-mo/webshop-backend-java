package com.backend.webshop.repositories

import com.backend.webshop.Exceptions.IdNotFoundException
import com.backend.webshop.model.OrderPositionResponse
import com.backend.webshop.persistence.*
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional
class OrderPositionRepository(
    private val positions: OrderPositionJpaRepository,
    private val orders: OrderJpaRepository,
    private val products: ProductJpaRepository
) {
    fun save(position: OrderPositionResponse) {
        val order = orders.findById(position.orderId)
            .orElseThrow { IdNotFoundException("Order with id ${position.orderId} not found") }
        val product = products.findById(position.productId)
            .orElseThrow { IdNotFoundException("Product with id ${position.productId} not found") }
        positions.save(OrderPositionEntity(position.id, order, product, position.quantity))
    }

    @Transactional(readOnly = true)
    fun findAllByOrderIds(orderIds: List<String>): List<OrderPositionResponse> =
        if (orderIds.isEmpty()) emptyList()
        else positions.findAllByOrderIdIn(orderIds).map { it.toResponse() }
}
