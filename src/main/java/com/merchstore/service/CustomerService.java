package com.merchstore.service;

import com.merchstore.model.Customer;
import com.merchstore.repository.CustomerRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer getAuthorizedCustomer() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        return customerRepository.findByEmail(loggedInUser.getName()).orElseThrow();
    }

    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    public void updateCustomerInfo(Customer newCustomer) {
        Customer old = getAuthorizedCustomer();
        old.setName(newCustomer.getName());
        old.setPhone(newCustomer.getPhone());
        customerRepository.save(old);
    }

    public boolean checkNoExistingEmail(String email) {
        return customerRepository.findByEmail(email).isEmpty();
    }
}
