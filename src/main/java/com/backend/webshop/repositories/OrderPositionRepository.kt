package com.backend.webshop.repositories

import com.backend.webshop.model.OrderPositionResponse
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service

@Service
class OrderPositionRepository(
        private val orderPositions: MutableList<OrderPositionResponse>
) {
    fun save(orderPositionResponse: OrderPositionResponse) {
        orderPositions.add(orderPositionResponse)
    }

}
