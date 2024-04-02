package com.merchstore.service;

import com.merchstore.model.Product;
import com.merchstore.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public List<Product> getProducts(String category) {
        if (category.isEmpty())
            return productRepository.findAll();
        else
            return productRepository.findProductsByCategory(category);
    }

}
