package com.backend.webshop.persistence

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Profile("demo")
class DemoData(
    private val customers: CustomerJpaRepository,
    private val products: ProductJpaRepository
) : ApplicationRunner {
    @Transactional
    override fun run(args: ApplicationArguments) {
        if (!customers.existsById("1")) {
            customers.save(CustomerEntity("1", "Demo", "Customer", "demo@example.com"))
        }
        if (products.count() == 0L) {
            products.saveAll(listOf(
                ProductEntity("demo-amd", "AMD Ryzen 9 5950X", "Desktop processor", 79900,
                    mutableListOf("AMD", "CPU", "Processor")),
                ProductEntity("demo-intel", "Intel Core i9-9900KF", "Desktop processor", 33900,
                    mutableListOf("Intel", "CPU", "Processor")),
                ProductEntity("demo-nvidia", "NVIDIA GeForce GTX 1080 Ti", "Graphics card, 11 GB", 74900,
                    mutableListOf("NVIDIA", "GPU"))
            ))
        }
    }
}
