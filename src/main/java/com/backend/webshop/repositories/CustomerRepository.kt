package com.backend.webshop.repositories

import com.backend.webshop.model.CustomerResponse
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class CustomerRepository {

    val customers = listOf(
            CustomerResponse(
                   "1",
                    "Mohammed",
                    "Osman",
                    "mohammedosman@blabla.com"
            )
    )
    fun findById(id: String): CustomerResponse? {
        return customers.find { it.id == id }
    }
}