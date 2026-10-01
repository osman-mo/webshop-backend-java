package com.backend.webshop.persistence

import com.backend.webshop.model.OrderStatus
import org.springframework.data.jpa.repository.JpaRepository

interface ProductJpaRepository : JpaRepository<ProductEntity, String>
interface CustomerJpaRepository : JpaRepository<CustomerEntity, String>
interface OrderJpaRepository : JpaRepository<OrderEntity, String> {
    fun findAllByCustomerIdAndStatus(customerId: String, status: OrderStatus): List<OrderEntity>
}
interface OrderPositionJpaRepository : JpaRepository<OrderPositionEntity, String> {
    fun findAllByOrderIdIn(orderIds: List<String>): List<OrderPositionEntity>
}
