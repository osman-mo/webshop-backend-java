package com.backend.webshop.repositories;

import com.backend.webshop.model.ProductResponse;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class ProductRepository {

    public List<ProductResponse> findAll(String tag){
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

        if(tag == null || tag == "")
            return products;

        else {
            tag = tag.toLowerCase();

            List<ProductResponse> filtered = new ArrayList<>();

            for (ProductResponse p : products) {

                //Turn all Tags to lowercase to enable non-case-sensitive filtering
                List<String> lowerCaseTags = new ArrayList<>();
                for(String t : p.getTags()){
                    lowerCaseTags.add(t.toLowerCase());
                }

                if (lowerCaseTags.contains(tag))
                    filtered.add(p);
            }

            return filtered;
        }
    }
}
