package com.zadeon.customermanagement.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "customers")
public class Customer{

    @Id
    @Column(nullable = false, updatable = false)
    private long id;
    //@Column(name = "cust_name")
    private String customerName;

    private String address;
    @Column(unique = true)
    private long phone;
    private String items;
    @Column(name = "total_cost")
    private double totalCost;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "purchase_date")
    private Date purchaseDate;

    public Customer(){

    }

    public Customer(long id, String customerName, String address, long phone, String items, double totalCost, Date purchaseDate) {
        super();
        this.id = id;
        this.customerName = customerName;
        this.address = address;
        this.phone = phone;
        this.items = items;
        this.totalCost = totalCost;
        this.purchaseDate = purchaseDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

}
