package com.merchstore.controller;

import com.merchstore.model.Customer;
import com.merchstore.service.CustomerService;
import com.merchstore.service.OrderService;
import com.merchstore.utils.wrappers.Address;
import com.merchstore.utils.wrappers.Card;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @PostMapping(value = "/checkout", params = "complete")
    public ModelAndView completeOrder(@ModelAttribute("customer") Customer customer,
                                      @ModelAttribute("address") Address address,
                                      @ModelAttribute("card") Card card
    ) {
        orderService.completeOrder(address, card);
        return new ModelAndView("redirect:/profile");
    }
    @PostMapping(value = "/checkout", params = "cancel")
    public ModelAndView cancelOrder(@ModelAttribute("customer") Customer customer,
                                    @ModelAttribute("address") Address address,
                                    @ModelAttribute("card") Card card
    ) {
        orderService.cancelOrder();
        return new ModelAndView("redirect:/collections");
    }

    @GetMapping("/cart")
    public ModelAndView goToCart() {
        ModelAndView modelAndView = new ModelAndView("checkout");
        Customer customer = customerService.getAuthorizedCustomer();

        return modelAndView.addObject("order", orderService.getOrCreatePendingOrder(customer))
                .addObject("customer", customer)
                .addObject("address", new Address())
                .addObject("card", new Card());

    }

    @PostMapping("/cart")
    public void addToCart(@ModelAttribute("productId") Long productId, @ModelAttribute("productQuantity") Integer quantity) {
        orderService.addToCart(productId, quantity);
    }
}
