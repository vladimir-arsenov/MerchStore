package com.merchstore.service;

import com.merchstore.model.Customer;
import com.merchstore.model.Order;
import com.merchstore.model.Product;
import com.merchstore.model.records.Address;
import com.merchstore.model.records.Card;
import com.merchstore.repository.OrderRepository;
import com.merchstore.utils.OrderStatus;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final CustomerService customerService;
    private final ProductService productService;
    private final OrderRepository orderRepository;

    public OrderService(CustomerService customerService, ProductService productService, OrderRepository orderRepository) {
        this.customerService = customerService;
        this.productService = productService;
        this.orderRepository = orderRepository;
    }


    public Order getPendingOrder(Customer customer) {
        for (Order order : customer.getOrders())
            if (order.getStatus().equals(OrderStatus.PENDING))
                return order;

        // no pending order - create new order
        Order newOrder = new Order();
        newOrder.setCustomer(customer);
        customer.getOrders().add(newOrder);

        return orderRepository.save(newOrder);
    }


    public void checkout(Order order, Address address, Card card) {
        order.setAddress(address.toString());
        order.setCard(card.toString());
        orderRepository.save(order);
    }


    public void addToCart(Long productId, Integer quantity) {
        Product product = productService.getById(productId);
        Order order = getPendingOrder(customerService.getAuthorizedCustomer());

        order.getItems().add(product);
        order.setPrice(order.getPrice() + product.getPrice());
        product.getOrders().add(order);
        product.setQuantity(quantity);

//        orderRepository.save(order);
    }
}
