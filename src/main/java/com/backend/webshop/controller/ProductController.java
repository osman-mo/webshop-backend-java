package com.backend.webshop.controller;

import com.backend.webshop.model.ProductResponse;
import com.backend.webshop.repositories.ProductRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class ProductController {

    ProductRepository productRepository = new ProductRepository();

    @GetMapping("/products")
    public List<ProductResponse> getAllProducts(@RequestParam(required = false) String tag) {

        return productRepository.findAll(tag);
    }
}
