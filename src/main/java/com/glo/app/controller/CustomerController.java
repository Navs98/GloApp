package com.glo.app.controller;

import com.glo.app.model.Customer;
import com.glo.app.model.Product;
import com.glo.app.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping
    public List<Customer> getAllCustomers(){
        return customerService.getAll();
    }

    @PostMapping("/add")
    public Customer addCustomer(@RequestBody Customer customer)  {
        try {
            return customerService.add(customer);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{customerId}")
    public Customer addCustomerProducts(@PathVariable int customerId, @RequestBody List<Product> products) throws IllegalArgumentException {
        if (ObjectUtils.isEmpty(customerId))
            throw new IllegalArgumentException("Customer_id cannot be empty");
        if (ObjectUtils.isEmpty(products))
            throw new IllegalArgumentException("Product list cannot be empty");
        for(Product product: products)
            if (ObjectUtils.isEmpty(product.getProductName()) || ObjectUtils.isEmpty(product.getProductPrice()))
                throw new IllegalArgumentException("Product details field cannot be empty. ");
        return customerService.addProducts(customerId, products);
    }

    @DeleteMapping("/{customerId}")
    public String deleteCustomer(@PathVariable int customerId) throws IllegalArgumentException {
        if (ObjectUtils.isEmpty(customerId))
            throw new IllegalArgumentException("Customer_Id cannot be empty");
        return customerService.delete(customerId);
    }
}
