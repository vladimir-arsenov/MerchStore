package com.merchstore.model;

import jakarta.persistence.*;
import lombok.Data;


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
    private double price;
    private int quantity;
    private String category;

//    @ToString.Exclude // to avoid recursion
//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "product_order",
//            joinColumns = @JoinColumn(name = "order_id"),
//            inverseJoinColumns = @JoinColumn(name = "product_id")
//    )
//    private List<Order> orders;
}
