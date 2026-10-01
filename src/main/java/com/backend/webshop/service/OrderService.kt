package com.backend.webshop.service

import com.backend.webshop.Exceptions.IdNotFoundException
import com.backend.webshop.Exceptions.EstoreException
import org.springframework.transaction.annotation.Transactional
import com.backend.webshop.model.*
import com.backend.webshop.repositories.CustomerRepository
import com.backend.webshop.repositories.OrderPositionRepository
import com.backend.webshop.repositories.OrderRepository
import com.backend.webshop.repositories.ProductRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.util.*

@Service
class OrderService(
        val orderRepository: OrderRepository,
        val orderPositionRepository: OrderPositionRepository,
        val customerRepository: CustomerRepository,
        val productRepository: ProductRepository
    )
{

    @Transactional
    fun createOrder(request: CreateOrderRequest): OrderResponse {
        customerRepository.findById(request.customerId)

        return orderRepository.save(request)
    }

    @Transactional
    fun createNewOrderPosition(
            orderId: String,
            request: CreateOrderPositionRequest
    ): OrderPositionResponse {

        if (request.quantity <= 0) {
            throw EstoreException("Quantity must be greater than zero", HttpStatus.BAD_REQUEST)
        }

        orderRepository.findById(orderId)
            ?: throw IdNotFoundException(
                message = "Order with id $orderId not found",
                statusCode = HttpStatus.BAD_REQUEST
            )

        if (productRepository.findById(request.productId).isEmpty)
            throw IdNotFoundException(
                message = "Product with id ${request.productId} not found",
                statusCode = HttpStatus.BAD_REQUEST
            )

        val orderPositionResponse = OrderPositionResponse(
            id = UUID.randomUUID().toString(),
            orderId = orderId,
            productId = request.productId,
            quantity = request.quantity
        )
        orderPositionRepository.save(orderPositionResponse)
        return orderPositionResponse
    }


}