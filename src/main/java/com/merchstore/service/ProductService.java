package com.merchstore.service;

import com.merchstore.model.Product;
import com.merchstore.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Objects;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> getAllProducts(int page) {
        Pageable pageable = PageRequest.of(page-1, 10);
        return productRepository.findAll(pageable);
    }

    public Page<Product> getProductsByCategory(String category, int page) {
        Pageable pageable = PageRequest.of(page-1, 10);
        return productRepository.findProductsByCategory(category, pageable);
    }

    public Product getById(Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    public Iterable<Product> search(String query) {
        return productRepository.search(query);
    }

    public Iterable<Product> getSuggestions(Long id) {
        Product p = productRepository.findById(id).orElseThrow();
        return Arrays.stream(p.getTitle().split(" "))
                .map(productRepository::search) // search for each word in title
                .flatMap(java.util.Collection::stream) // combine all the search results into one stream
                .distinct() // remove duplicate products
                .filter(product -> !Objects.equals(product.getId(), p.getId()) // remove product with the same id
                        && product.getCategory().equals(p.getCategory())) // filter out products with different category
                .limit(10)
                .toList();
    }
}
