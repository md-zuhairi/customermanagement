package com.zadeon.customermanagement.controller;

import com.zadeon.customermanagement.entity.Customer;
import com.zadeon.customermanagement.service.customerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/customers")
public class customerController {
    private customerService service;

    public customerController(customerService service){
        super();
        this.service = service;
    }

    @GetMapping("/all")
    public String getAllCustomers(Model model, @Param("keyword") String keyword){
        model.addAttribute("customers", service.getAllCustomers(keyword));
        model.addAttribute("keyword", keyword);
        return "customers";
    }

    @GetMapping("/new")
    public String addNewCustomer(Model model)
    {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "add_customer";
    }

    @PostMapping("/all")
    public String saveCustomer(@ModelAttribute("customer") Customer customer){
        service.saveCustomer(customer);
        return "redirect:/customers/all";
    }

    @GetMapping("/edit/{id}")
    public String editCustomer(@PathVariable Long id, Model model){
        model.addAttribute("customer", service.getCustomerById(id));
        return "edit_customer";
    }

    @PostMapping("/{id}")
    public String updateCustomer(@PathVariable Long id, @ModelAttribute("customer") Customer customer, Model model){
        //Getting Customer from DB to update by ID
        Customer oldCustomer = service.getCustomerById(id);
        oldCustomer.setId(customer.getId());
        oldCustomer.setCustomerName(customer.getCustomerName());
        oldCustomer.setAddress(customer.getAddress());
        oldCustomer.setPhone(customer.getPhone());
        oldCustomer.setItems(customer.getItems());
        oldCustomer.setTotalCost(customer.getTotalCost());
        oldCustomer.setPurchaseDate(customer.getPurchaseDate());

        service.updateCustomer(oldCustomer);
        return "redirect:/customers/all";

    }

    @GetMapping("/delete")
    public String getAllCustomersToDelete(Model model){
        model.addAttribute("customers", service.getAllCustomers(null));
        return "delete_customer";
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteCustomer(@PathVariable Long id){
        service.deleteCustomerById(id);
        return "redirect:/customers/all";
    }
    @PostMapping("/search")
    public String showCustomerById(@ModelAttribute("customer") Customer customer, Model model) {
        model.addAttribute("customer", service.getCustomerById(customer.getId()));
        return "redirect:/customers/search";
    }
}
