package com.merchstore.service;

import com.merchstore.model.Customer;
import com.merchstore.model.Order;
import com.merchstore.model.Product;
import com.merchstore.repository.OrderRepository;
import com.merchstore.utils.OrderStatus;
import com.merchstore.utils.records.Address;
import com.merchstore.utils.records.Card;
import org.springframework.stereotype.Service;

import java.util.HashMap;

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


    public Order getOrCreatePendingOrder(Customer customer) {
        for (Order order : customer.getOrders())
            if (order.getStatus().equals(OrderStatus.PENDING))
                return order;

        // no pending order - create new order
        Order newOrder = Order.builder()
                .status(OrderStatus.PENDING)
                .items(new HashMap<>())
                .customer(customer)
                .build();

        customer.getOrders().add(newOrder);

        return orderRepository.save(newOrder);
    }


    public void completeOrder(Address address, Card card) {
        Order order = getOrCreatePendingOrder(customerService.getAuthorizedCustomer());
        order.setAddress(address.toString());
        order.setCard(card.toString());
        order.setStatus(OrderStatus.PROCESSING);
        orderRepository.save(order);
    }


    public void addToCart(Long productId, Integer quantity) {
        Product product = productService.getById(productId);
        Order order = getOrCreatePendingOrder(customerService.getAuthorizedCustomer());

        order.getItems().put(product, order.getItems().getOrDefault(product, 0) + quantity);
        order.setPrice(order.getPrice() + product.getPrice() * quantity);
        order.setQuantity(order.getQuantity() + quantity);

        orderRepository.save(order);
    }

    public void cancelOrder() {
        Order order = getOrCreatePendingOrder(customerService.getAuthorizedCustomer());
        orderRepository.delete(order);
    }
}
