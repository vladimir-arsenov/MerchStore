package com.merchstore.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true)
    private String email;
    private String password;
    private String role;
    private String phone;
    private String name;


    @ToString.Exclude
    @OneToMany(mappedBy = "customer")
    private List<Order> orders;
}
