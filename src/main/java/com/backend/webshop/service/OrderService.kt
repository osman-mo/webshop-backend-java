package com.backend.webshop.service

import com.backend.webshop.model.*
import com.backend.webshop.repositories.CustomerRepository
import com.backend.webshop.repositories.OrderPositionRepository
import com.backend.webshop.repositories.OrderRepository
import com.backend.webshop.repositories.ProductRepository
import org.springframework.stereotype.Service
import java.lang.Exception
import java.util.*

@Service
class OrderService(
        val orderRepository: OrderRepository,
        val orderPositionRepository: OrderPositionRepository,
        val customerRepository: CustomerRepository,
        val productRepository: ProductRepository
    )
{

    fun createOrder(request: CreateOrderRequest): OrderResponse {
        val customer: CustomerResponse = customerRepository.findById(request.customerId)
               ?: throw Exception("Customer not found")

        return orderRepository.save(request)
    }

    fun createNewOrderPosition(
            orderId: String,
            request: CreateOrderPositionRequest
    ): OrderPositionResponse {

        orderRepository.findById(orderId) ?: throw Exception("Order not found")

        if (productRepository.findById(request.productId).isEmpty)
            throw Exception("Product not found")

        val orderPositionResponse = OrderPositionResponse(
            id = UUID.randomUUID().toString(),
            productId = request.productId,
            quantity = request.quantity
        )
        orderPositionRepository.save(orderPositionResponse)
        return orderPositionResponse
    }


}