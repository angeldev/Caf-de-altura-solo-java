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
	        Order order = orderRepository.findById(id);
	        if (order == null) {
	            throw new RuntimeException("Pedido no encontrado con id: " + id);
	        }
	        return order;
	    }

	    public Order createOrder(Order order) {
	        // Validar cliente
	        if (order.getCustomer() == null || order.getCustomer().getId() == null ||
	            customerRepository.findById(order.getCustomer().getId()) == null) {
	            throw new RuntimeException("Cliente no correcto.");
	        }

	        // Validar items
	        if (order.getItems() == null || order.getItems().isEmpty()) {
	            throw new RuntimeException("El pedido debe contener al menos un artículo.");
	        }

	        // Validar cada item
	        for (OrderItem item : order.getItems()) {
	            if (item.getCoffee() == null || item.getCoffee().getId() == null ||
	                coffeeRepository.findById(item.getCoffee().getId()) == null) {
	                throw new RuntimeException("Invalid coffee in order items");
	            }
	            if (item.getQuantity() == null || item.getQuantity() <= 0) {
	                throw new RuntimeException("Café no encontrado en los artículos del pedido.");
	            }
	            // Establecer el precio actual del café
	            item.setPrice(coffeeRepository.findById(item.getCoffee().getId()).getPrecio());
	        }

	        // Establecer fecha de creación
	        order.setCreatedAt(LocalDateTime.now());
	        
	        // Calcular el total
	        order.calculateTotal();

	        return orderRepository.save(order);
	    }

	    public List<Order> getOrdersByCustomerId(Long customerId) {
	        if (customerRepository.findById(customerId) == null) {
	            throw new RuntimeException("Pedido no encontrado con id: " + customerId);
	        }
	        return orderRepository.findByCustomerId(customerId);
	    }
}
