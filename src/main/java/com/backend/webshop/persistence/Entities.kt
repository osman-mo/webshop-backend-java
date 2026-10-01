package com.backend.webshop.persistence

import com.backend.webshop.model.*
import jakarta.persistence.*
import java.time.LocalDateTime
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes

@Entity
@Table(name = "products")
class ProductEntity(
    @Id var id: String,
    @Column(nullable = false) var name: String,
    @Column(nullable = false, length = 2000) var description: String,
    @Column(name = "price_in_cent", nullable = false) var priceInCent: Int,
    @ElementCollection
    @CollectionTable(name = "product_tags", joinColumns = [JoinColumn(name = "product_id")])
    @Column(name = "tag", nullable = false)
    @OrderColumn(name = "tag_index")
    var tags: MutableList<String> = mutableListOf()
)

@Entity
@Table(name = "customers")
class CustomerEntity(
    @Id var id: String,
    @Column(name = "first_name", nullable = false) var firstName: String,
    @Column(name = "last_name", nullable = false) var lastName: String,
    @Column(nullable = false) var email: String
)

@Entity
@Table(name = "shop_orders")
class OrderEntity(
    @Id var id: String,
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    var customer: CustomerEntity,
    @Column(name = "order_time", nullable = false) var orderTime: LocalDateTime,
    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(nullable = false, length = 20) var status: OrderStatus
)

@Entity
@Table(name = "order_positions")
class OrderPositionEntity(
    @Id var id: String,
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false) var order: OrderEntity,
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false) var product: ProductEntity,
    @Column(nullable = false) var quantity: Long
)

fun ProductEntity.toResponse() = ProductResponse(id, name, description, priceInCent, tags.toList())
fun CustomerEntity.toResponse() = CustomerResponse(id, firstName, lastName, email)
fun OrderEntity.toResponse() = OrderResponse(id, customer.id, orderTime, status, emptyList())
fun OrderPositionEntity.toResponse() = OrderPositionResponse(id, order.id, product.id, quantity)
