package com.zadeon.customermanagement.service;

import com.zadeon.customermanagement.entity.Customer;

import java.util.List;

public interface customerService {
    List<Customer> getAllCustomers(String keyword);

    Customer saveCustomer(Customer customer);

    Customer updateCustomer(Customer customer);

    Customer getCustomerById(Long id);

    void deleteCustomerById(Long id);



}
