package com.gammatech.cafateria.repository;

import com.gammatech.cafateria.model.Order;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class OrderRepository {
	  private final Map<Long, Order> orders = new ConcurrentHashMap<>();
	    private final AtomicLong idGenerator = new AtomicLong();

	    public List<Order> findAll() {
	        return new ArrayList<>(orders.values());
	    }

	    public Order findById(Long id) {
	        return orders.get(id);
	    }

	    public Order save(Order order) {
	        if (order.getId() == null) {
	            order.setId(idGenerator.incrementAndGet());
	        }
	        orders.put(order.getId(), order);
	        return order;
	    }

	    public List<Order> findByCustomerId(Long customerId) {
	        return orders.values().stream()
	                .filter(order -> order.getCustomer().getId().equals(customerId))
	                .collect(Collectors.toList());
	    }
}
