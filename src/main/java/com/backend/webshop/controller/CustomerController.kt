package com.backend.webshop.controller

import com.backend.webshop.model.CustomerResponse
import com.backend.webshop.repositories.CustomerRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class CustomerController {
    val customerRepository: CustomerRepository = CustomerRepository()
    @GetMapping("/customers/{id}")
    fun getCustomerById(
            @PathVariable id: String
    ): ResponseEntity<CustomerResponse>
    {
        val response = customerRepository.findById(id)
        return if(response != null)
            ResponseEntity.ok(response)
        else
            ResponseEntity.notFound().build()
    }
}