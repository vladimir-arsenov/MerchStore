package com.merchstore.controller;

import com.merchstore.model.records.Address;
import com.merchstore.model.records.Card;
import com.merchstore.model.Customer;
import com.merchstore.model.Order;
import com.merchstore.service.CustomerService;
import com.merchstore.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RestController
public class OrderController {

    private final CustomerService customerService;
    private final OrderService orderService;

    public OrderController(CustomerService customerService, OrderService orderService) {
        this.customerService = customerService;
        this.orderService = orderService;
    }

    @PostMapping("/checkout")
    public ModelAndView checkout(@RequestBody Order order, @RequestBody Address address, @RequestBody Card card) {
        orderService.checkout(order, address, card);
        return new ModelAndView("product_overview");
    }

    @GetMapping("/checkout")
    public ModelAndView checkout() {
        ModelAndView modelAndView = new ModelAndView("checkout");
        Customer customer = customerService.getAuthorizedCustomer();
        modelAndView.addObject("order", orderService.getPendingOrder(customer));
        modelAndView.addObject("customer", customer);
        log.info("CUSTOMER: " + customer);
        log.info("ORDER FROM GET PENDING ORDER: " + orderService.getPendingOrder(customer).getItems());

        return modelAndView;
    }

    @PostMapping("/cart")
    public void addToCart(@ModelAttribute("productId") Long productId, @ModelAttribute("productQuantity") Integer quantity) {
        System.out.println("CONTROLLER" +productId +  " " + quantity);
        orderService.addToCart(productId, quantity);

//        return new ModelAndView("ch");
    }
}
