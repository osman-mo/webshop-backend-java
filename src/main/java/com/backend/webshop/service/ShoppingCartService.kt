package com.backend.webshop.service

import com.backend.webshop.Exceptions.IdNotFoundException
import com.backend.webshop.model.OrderPositionResponse
import com.backend.webshop.model.OrderResponse
import com.backend.webshop.model.ProductResponse
import com.backend.webshop.model.ShoppingCartResponse
import com.backend.webshop.repositories.OrderPositionRepository
import com.backend.webshop.repositories.OrderRepository
import com.backend.webshop.repositories.ProductRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ShoppingCartService(
    private val orderRepository: OrderRepository,
    private val orderPositionRepository: OrderPositionRepository,
    private val productRepository: ProductRepository
) {
    fun getShoppingCartForCustomer(customerId: String): ShoppingCartResponse {

        val orders: List<OrderResponse> = orderRepository.findAllNewOrdersByCustomerId(customerId)
        val orderIds = orders.map { it.id }

        val orderPositions: List<OrderPositionResponse> = orderPositionRepository.findAllByOrderIds(orderIds)

        val deliveryCost = 500L
        val totalCost = calculateSumForCart(orderPositions, deliveryCost)

        return ShoppingCartResponse(
            customerId = customerId,
            orderPositions = orderPositions,
            deliveryOption = "STANDARD",
            totalCostInCent = totalCost,
            deliveryCostInCent = deliveryCost,
        )
    }

    fun calculateSumForCart(
        orderPositions: List<OrderPositionResponse>,
        deliveryCost: Long
    ): Long {
        val positionAmounts: List<Long> = orderPositions.map {
            val product: ProductResponse = productRepository
                .findById(it.productId)
                .orElseThrow{throw IdNotFoundException("Product with id ${it.productId} not found")}
            it.quantity * product.priceInCent
        }
        return positionAmounts.sumOf { it } + deliveryCost
    }

}
