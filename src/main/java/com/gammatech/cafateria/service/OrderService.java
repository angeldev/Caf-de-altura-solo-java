package com.gammatech.cafateria.service;

import com.gammatech.cafateria.model.Order;
import com.gammatech.cafateria.model.OrderItem;
import com.gammatech.cafateria.repository.OrderRepository;
import com.gammatech.cafateria.repository.CustomerRepository;
import com.gammatech.cafateria.repository.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private CoffeeRepository coffeeRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Pedido no encontrado con id: " + id));
    }

    public Order createOrder(Order order) {
        // Validar cliente
        if (order.getCustomer() == null || order.getCustomer().getId() == null ||
            !customerRepository.existsById(order.getCustomer().getId())) {
            throw new RuntimeException("Cliente no válido");
        }

        // Validar items
        if (order.getItems() == null || order.getItems().isEmpty()) {
            throw new RuntimeException("El pedido debe contener al menos un artículo");
        }

        // Validar cada item
        for (OrderItem item : order.getItems()) {
            if (item.getCoffee() == null || item.getCoffee().getId() == null ||
                !coffeeRepository.existsById(item.getCoffee().getId())) {
                throw new RuntimeException("Café no válido en los artículos del pedido");
            }
            if (item.getQuantity() == null || item.getQuantity() <= 0) {
                throw new RuntimeException("La cantidad debe ser mayor que 0");
            }
            
            // Establecer el precio actual del café
            coffeeRepository.findById(item.getCoffee().getId())
                .ifPresent(coffee -> item.setPrice(coffee.getPrice()));
                
            // Establecer la referencia a la orden
            item.setOrder(order);
        }

        // Establecer fecha de creación
        order.setCreatedAt(LocalDateTime.now());
        
        // Calcular el total
        order.calculateTotal();

        return orderRepository.save(order);
    }

    public List<Order> getOrdersByCustomerId(Long customerId) {
        if (!customerRepository.existsById(customerId)) {
            throw new RuntimeException("Cliente no encontrado con id: " + customerId);
        }
        return orderRepository.findByCustomerId(customerId);
    }
}

