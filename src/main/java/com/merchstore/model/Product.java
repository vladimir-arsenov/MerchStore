package com.merchstore.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;


@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;
    private String title;
    private String description;
    private Float price;
    private Integer quantity;
    private String category;

    @ToString.Exclude // to avoid recursion
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "product_order",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Order> orders;
}
