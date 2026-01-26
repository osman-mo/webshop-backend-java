package com.backend.webshop.controller

import com.backend.webshop.model.CustomerResponse
import com.backend.webshop.model.ShoppingCartResponse
import com.backend.webshop.repositories.CustomerRepository
import com.backend.webshop.service.ShoppingCartService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class CustomerController(
    val customerRepository: CustomerRepository,
    val shoppingCartService: ShoppingCartService
) {

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

    @GetMapping("/customers/{id}/shoppingcart")
    fun getShoppingCartByCustomerId(
        @PathVariable id: String
    ): ShoppingCartResponse {
        return shoppingCartService.getShoppingCartForCustomer(id)
    }

}

