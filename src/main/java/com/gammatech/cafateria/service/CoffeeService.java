package com.gammatech.cafateria.service;

import com.gammatech.cafateria.model.Coffee;
import com.gammatech.cafateria.repository.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CoffeeService {
    @Autowired
    private CoffeeRepository coffeeRepository;

    public List<Coffee> getAllCoffees() {
        return coffeeRepository.findAll();
    }

    public Page<Coffee> getAllCoffeesPaged(Pageable pageable) {
        return coffeeRepository.findAll(pageable);
    }

    public Coffee getCoffeeById(Long id) {
        return coffeeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Coffee not found with id: " + id));
    }

    public Coffee createCoffee(Coffee coffee) {
        // Validar nombre
        if (coffee.getName() == null || coffee.getName().trim().isEmpty()) {
            throw new RuntimeException("Coffee name is required");
        }

        // Validar origen
        if (coffee.getOrigin() == null || coffee.getOrigin().trim().isEmpty()) {
            throw new RuntimeException("Coffee origin is required");
        }

        // Validar nivel de tostado
        if (coffee.getRoastLevel() == null || coffee.getRoastLevel().trim().isEmpty()) {
            throw new RuntimeException("Coffee roast level is required");
        }

        // Validar precio
        if (coffee.getPrice() == null || coffee.getPrice() <= 0) {
            throw new RuntimeException("Coffee price must be greater than 0");
        }

        // Validar stock
        if (coffee.getStockQuantity() == null || coffee.getStockQuantity() < 0) {
            throw new RuntimeException("Coffee stock quantity must be 0 or greater");
        }

        return coffeeRepository.save(coffee);
    }

    public Coffee updateCoffee(Long id, Coffee coffee) {
        if (!coffeeRepository.existsById(id)) {
            throw new RuntimeException("Coffee not found with id: " + id);
        }

        // Validar nombre
        if (coffee.getName() == null || coffee.getName().trim().isEmpty()) {
            throw new RuntimeException("Coffee name is required");
        }

        // Validar origen
        if (coffee.getOrigin() == null || coffee.getOrigin().trim().isEmpty()) {
            throw new RuntimeException("Coffee origin is required");
        }

        // Validar nivel de tostado
        if (coffee.getRoastLevel() == null || coffee.getRoastLevel().trim().isEmpty()) {
            throw new RuntimeException("Coffee roast level is required");
        }

        // Validar precio
        if (coffee.getPrice() == null || coffee.getPrice() <= 0) {
            throw new RuntimeException("Coffee price must be greater than 0");
        }

        // Validar stock
        if (coffee.getStockQuantity() == null || coffee.getStockQuantity() < 0) {
            throw new RuntimeException("Coffee stock quantity must be 0 or greater");
        }

        coffee.setId(id);
        return coffeeRepository.save(coffee);
    }

    public Coffee patchCoffee(Long id, Coffee coffee) {
        Coffee existingCoffee = getCoffeeById(id);
        
        if (coffee.getName() != null && !coffee.getName().trim().isEmpty()) {
            existingCoffee.setName(coffee.getName());
        }
        if (coffee.getOrigin() != null && !coffee.getOrigin().trim().isEmpty()) {
            existingCoffee.setOrigin(coffee.getOrigin());
        }
        if (coffee.getRoastLevel() != null && !coffee.getRoastLevel().trim().isEmpty()) {
            existingCoffee.setRoastLevel(coffee.getRoastLevel());
        }
        if (coffee.getPrice() != null && coffee.getPrice() > 0) {
            existingCoffee.setPrice(coffee.getPrice());
        }
        if (coffee.getStockQuantity() != null && coffee.getStockQuantity() >= 0) {
            existingCoffee.setStockQuantity(coffee.getStockQuantity());
        }

        return coffeeRepository.save(existingCoffee);
    }

    public void deleteCoffee(Long id) {
        if (!coffeeRepository.existsById(id)) {
            throw new RuntimeException("Coffee not found with id: " + id);
        }
        coffeeRepository.deleteById(id);
    }
}
