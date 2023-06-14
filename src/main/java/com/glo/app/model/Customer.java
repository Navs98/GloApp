package com.glo.app.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int customerId;

    private String customerName;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Product> productList;
}
