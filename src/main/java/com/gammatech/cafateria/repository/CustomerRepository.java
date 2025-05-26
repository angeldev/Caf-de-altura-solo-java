package com.gammatech.cafateria.repository;

import com.gammatech.cafateria.model.Customer;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class CustomerRepository {
    private final Map<Long, Customer> customers = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    public List<Customer> findAll() {
        return new ArrayList<>(customers.values());
    }

    public Customer findById(Long id) {
        return customers.get(id);
    }

    public Customer save(Customer customer) {
        if (customer.getId() == null) {
            customer.setId(idGenerator.incrementAndGet());
        }
        customers.put(customer.getId(), customer);
        return customer;
    }

    public void deleteById(Long id) {
        customers.remove(id);
    }
}
