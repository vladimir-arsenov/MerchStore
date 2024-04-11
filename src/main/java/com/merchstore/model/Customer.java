package com.merchstore.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String password;
    private String role;
    private String email;
    private String phone;
    private String name;


    @OneToMany(mappedBy = "customer")
    private List<Order> orders;
}
