package com.zadeon.customermanagement.controller;

import com.zadeon.customermanagement.entity.Customer;
import com.zadeon.customermanagement.entity.User;
import com.zadeon.customermanagement.service.UserService;
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
    @Autowired
    private customerService service;

    @Autowired
    private UserService userService;

    public customerController(customerService service){
        super();
        this.service = service;
    }

    @GetMapping("/all")
    public String getAllCustomers(Model model, @Param("keyword") String keyword){
            model.addAttribute("customers", service.getAllCustomers(keyword));
            model.addAttribute("keyword", keyword);
            model.addAttribute("username", "Hakkim");
            return "customers";
    }

    @GetMapping("/sales")
    public String getSales(Model model, @Param("month") String month, @Param("year") String year){
        model.addAttribute("total", service.getSales(month, year));
        model.addAttribute("month", month);
        model.addAttribute("year", year);
        return "sales";
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

    @RequestMapping(value = "/deletePage", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String getAllCustomersToDelete(Model model){
        model.addAttribute("customers", service.getAllCustomers(null));
        return "delete_customer";
    }

//    @RequestMapping(value = "/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
//    public String deleteCustomer(@PathVariable Long id){
//        service.deleteCustomerById(id);
//        return "redirect:/customers/all";
//    }
    @PostMapping("/search")
    public String showCustomerById(@ModelAttribute("customer") Customer customer, Model model) {
        model.addAttribute("customer", service.getCustomerById(customer.getId()));
        return "redirect:/customers/search";
    }
    @RequestMapping(value = "/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(Long id){
        service.deleteCustomerById(id);
        return "redirect:/customers/deletePage";
    }
    @GetMapping("/register")
    public String getRegisterPage(Model model){
        model.addAttribute("registerRequest", new User());
        return "register";
    }
    @GetMapping("/login")
    public String getLoginPage(Model model){
        model.addAttribute("loginRequest", new User());
        return "login_user";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user){
        System.out.println("Register request: " + user);
        User registeredUser = userService.registerUser(user.getLogin(), user.getPassword(), user.getEmail());
        return registeredUser == null ? "error_page" : "redirect:/customers/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model){
        System.out.println("login request: " + user);
        User authenticated = userService.authenticate(user.getLogin(), user.getPassword());
        //model.addAttribute("username", authenticated.getLogin());
        return authenticated == null ? "error_page" : "redirect:/customers/all";
    }
}
