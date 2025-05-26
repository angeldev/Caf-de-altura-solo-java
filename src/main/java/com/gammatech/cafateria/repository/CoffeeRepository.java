package com.gammatech.cafateria.repository;

import com.gammatech.cafateria.model.Coffee;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class CoffeeRepository {
	 private final Map<Long, Coffee> coffees = new ConcurrentHashMap<>();
	    private final AtomicLong idGenerator = new AtomicLong();

	    public List<Coffee> findAll() {
	        return new ArrayList<>(coffees.values());
	    }

	    public Coffee findById(Long id) {
	        return coffees.get(id);
	    }

	    public Coffee save(Coffee coffee) {
	        if (coffee.getId() == null) {
	            coffee.setId(idGenerator.incrementAndGet());
	        }
	        coffees.put(coffee.getId(), coffee);
	        return coffee;
	    }

	    public void deleteById(Long id) {
	        coffees.remove(id);
	    }
}
