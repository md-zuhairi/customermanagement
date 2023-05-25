package com.zadeon.customermanagement.serviceImpl;

import com.zadeon.customermanagement.entity.Customer;
import com.zadeon.customermanagement.respository.customerRepository;
import com.zadeon.customermanagement.service.customerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class customerSeviceImpl implements customerService {
    private customerRepository customerRepo;

    public customerSeviceImpl(customerRepository customerRepo){
        super();
        this.customerRepo = customerRepo;
    }

    public List<Customer> getAllCustomers(){
        return customerRepo.findAll();
    }

    public Customer saveCustomer(Customer customer){
        return customerRepo.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    @Override
    public Customer getCustomerById(Long id) {

        return customerRepo.findById(id).get();
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepo.deleteById(id);
    }
}
