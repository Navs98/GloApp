package com.glo.app.service;

import com.glo.app.model.Customer;
import com.glo.app.model.Product;
import com.glo.app.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer add(Customer customer) throws IllegalArgumentException {
        if (customer.getCustomerName().is empty() || customer.getCustomerName().isBlank())
            throw new IllegalArgumentException("Name field cannot be blank");
        return customerRepository.save(customer);
    }

    @Override
    public Customer addProducts(int customerId, List<Product> products) throws IllegalArgumentException {
        checkCustomerExists(customerId);
        Customer customer = customerRepository.findById(customerId).get();
        List<Product> list = customer.getProductList();
        list.addAll(products);
        customer.setProductList(list);
        customerRepository.save(customer);
        return customerRepository.findById(customerId).get();
    }

    @Override
    public String delete(int customerId) throws IllegalArgumentException {
        checkCustomerExists(customerId);
        customerRepository.delete(customerRepository.findById(customerId).get());
        return "Deleted successfully";
    }

    public void checkCustomerExists(int customerId) throws IllegalArgumentException {
        if (customerRepository.findById(customerId).isEmpty())
            throw new IllegalArgumentException("Customer_id not found: ");
    }
}
