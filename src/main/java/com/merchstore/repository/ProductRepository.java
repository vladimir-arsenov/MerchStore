package com.merchstore.repository;

import com.merchstore.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findProductsByCategory(String category, Pageable pageable);

    @Query(value = "select p from Product p where p.title like %?1% or p.description like %?1%")
    List<Product> search(String query);
}
