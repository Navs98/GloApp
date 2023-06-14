package com.glo.app.service;

import com.glo.app.model.Customer;
import com.glo.app.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerService {
    Customer add(Customer customer) throws IllegalArgumentException;

    Customer addProducts(int customerId, List<Product> products) throws IllegalArgumentException;

    String delete(int customerId) throws IllegalArgumentException;

    List<Customer> getAll();
}
