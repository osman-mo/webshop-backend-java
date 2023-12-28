package com.backend.webshop.repositories;

import com.backend.webshop.model.ProductResponse;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductRepository {

    List<ProductResponse> products = Arrays.asList(
            new ProductResponse(
                    "1",
                    "AMD Ryzen 9 5950X",
                    "very good gpu",
                    79900,
                    Arrays.asList("AMD", "GPU", "Processor")
            ),
            new ProductResponse(
                    "2",
                    "INtel Core 19-9900KF",
                    "good gpu",
                    33900,
                    Arrays.asList("Intel", "GPU", "Processor")
            ),
            new ProductResponse(
                    "3",
                    "NVIDIA GeForce GTX 1080 Ti Black Edition 11GB",
                    "very good gpu",
                    74900,
                    Arrays.asList("NVIDIA", "GPU", "Processor")
            )
    );

    public List<ProductResponse> findAll(String tag){

        if(tag == null)
            return products;

        else {
            String lowercaseTag = tag.toLowerCase();

            return  products.stream()
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

}
