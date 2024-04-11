package com.merchstore.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double price;
    private String status; // CLOSED, PROCESSING, PENDING
    private String card;
    private String address;

    @ToString.Exclude // to avoid recursion
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;


    @ManyToMany(mappedBy = "orders")
    private List<Product> items;
}
