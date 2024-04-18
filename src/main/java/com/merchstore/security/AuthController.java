package com.merchstore.security;

import com.merchstore.model.Customer;
import com.merchstore.service.CustomerService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;



@RestController
public class AuthController {
    private final CustomerService customerService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(CustomerService customerService, PasswordEncoder passwordEncoder) {
        this.customerService = customerService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ModelAndView createUser(@ModelAttribute("customer") Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customer.setRole("USER");
        if (customerService.checkNoExistingEmail(customer.getEmail()))
            customerService.save(customer);
        else
            return new ModelAndView("register").addObject("error", "This email is taken");

        return new ModelAndView("redirect:/profile");
    }

    @GetMapping("/register")
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }
}
