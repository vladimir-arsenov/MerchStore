package com.merchstore.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double price;
    private int quantity;
    private String status; // CLOSED, PROCESSING, PENDING
    private String card;
    private String address;

    @ToString.Exclude // to avoid recursion
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;


//    @ManyToMany(mappedBy = "orders")
//    private List<Product> items;

    @ElementCollection
    @CollectionTable(name="order_item_mapping", joinColumns=@JoinColumn(name="order_id"))
    @Column(name="quantity") // name of the value column
    @MapKeyJoinColumn(name="product_id") // name of the key column
    private Map<Product, Integer> items = new HashMap<>();
}
