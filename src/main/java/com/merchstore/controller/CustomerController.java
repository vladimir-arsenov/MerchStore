package com.merchstore.controller;

import com.merchstore.model.Customer;
import com.merchstore.service.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/profile")
    public ModelAndView profile() {
        ModelAndView modelAndView = new ModelAndView("profile");
        Customer customer = customerService.getAuthorizedCustomer();
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }
}
