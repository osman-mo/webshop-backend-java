package com.backend.webshop.repositories;

import com.backend.webshop.model.ProductCreateRequest;
import com.backend.webshop.model.ProductResponse;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductRepository {

    public static ProductRepository theRepository;
    public static ProductRepository getProductRepository(){
        if( theRepository == null){
            theRepository = new ProductRepository();
        }
        return theRepository;
    }
    List<ProductResponse> products = new ArrayList<>();

    public ProductRepository() {
        products.add(
                new ProductResponse(
                        UUID.randomUUID().toString(),
                        "AMD Ryzen 9 5950X",
                        "very good gpu",
                        79900,
                        Arrays.asList("AMD", "GPU", "Processor")
                ));
        products.add(
                new ProductResponse(
                        UUID.randomUUID().toString(),
                        "INtel Core 19-9900KF",
                        "good gpu",
                        33900,
                        Arrays.asList("Intel", "GPU", "Processor")
                ));
        products.add(
                new ProductResponse(
                        UUID.randomUUID().toString(),
                        "NVIDIA GeForce GTX 1080 Ti Black Edition 11GB",
                        "very good gpu",
                        74900,
                        Arrays.asList("NVIDIA", "GPU", "Processor")
                ));
    }

    public List<ProductResponse> findAll(String tag) {

        if (tag == null)
            return products;

        else {
            String lowercaseTag = tag.toLowerCase();

            return products.stream()
                    .filter(p -> lowercaseTags(p).contains(lowercaseTag))
                    .collect(Collectors.toList());
        }
    }

    private static List<String> lowercaseTags(ProductResponse p) {
        List<String> tags = p.getTags();

        return tags.stream()
                .map(tag -> tag.toLowerCase())
                .collect(Collectors.toList());
    }

    public Optional<ProductResponse> findById(String id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    public void deleteById(String id) {
        this.products = products.stream()
                .filter(p -> !p.getId().equals(id))
                .collect(Collectors.toList());

    }

    public ProductResponse save(ProductCreateRequest request) {
        ProductResponse response = new ProductResponse(
                UUID.randomUUID().toString(),
                request.getName(),
                request.getDescription(),
                request.getPriceInCent(),
                request.getTags()
        );
        products.add(response);
        return response;
    }
}
