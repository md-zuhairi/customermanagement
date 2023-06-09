package com.zadeon.customermanagement.respository;

import com.zadeon.customermanagement.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface customerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT c FROM Customer c WHERE CONCAT(c.id, c.customerName, c.address, c.phone, c.items, c.totalCost, c.purchaseDate) LIKE %?1%")
    public List<Customer> search(String keyword);

    @Query("SELECT SUM(c.totalCost) FROM Customer c WHERE MONTH(c.purchaseDate) = ?1 AND YEAR(c.purchaseDate) = ?2")
    public Double getSales(String month, String year);
}
