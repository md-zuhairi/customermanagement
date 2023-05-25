package com.zadeon.customermanagement.service;

import com.zadeon.customermanagement.entity.Customer;

import java.util.List;

public interface customerService {
    public List<Customer> getAllCustomers();

    Customer saveCustomer(Customer customer);

    Customer updateCustomer(Customer customer);

    Customer getCustomerById(Long id);

    void deleteCustomerById(Long id);



}
