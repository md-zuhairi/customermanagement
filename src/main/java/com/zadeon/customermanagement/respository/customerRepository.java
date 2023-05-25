package com.zadeon.customermanagement.respository;

import com.zadeon.customermanagement.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface customerRepository extends JpaRepository<Customer, Long> {
}
