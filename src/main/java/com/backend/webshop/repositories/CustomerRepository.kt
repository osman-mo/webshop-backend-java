package com.backend.webshop.repositories

import com.backend.webshop.Exceptions.IdNotFoundException
import com.backend.webshop.model.CustomerResponse
import com.backend.webshop.persistence.*
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class CustomerRepository(private val customers: CustomerJpaRepository) {
    @Transactional(readOnly = true)
    fun findById(id: String): CustomerResponse = customers.findById(id)
        .orElseThrow { IdNotFoundException("Customer with id $id not found") }.toResponse()
}
