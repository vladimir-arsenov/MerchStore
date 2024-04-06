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

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findProductsByCategory(category);
    }

    public Product getById(Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    public List<Product> search(String query) {
        return productRepository.search(query);
    }

}
