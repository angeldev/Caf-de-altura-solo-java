package com.gammatech.cafateria.service;

import com.gammatech.cafateria.model.Coffee;
import com.gammatech.cafateria.repository.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CoffeeService {
	@Autowired
    private CoffeeRepository coffeeRepository;

    public List<Coffee> getAllCoffees() {
        return coffeeRepository.findAll();
    }

    public Coffee getCoffeeById(Long id) {
        Coffee coffee = coffeeRepository.findById(id);
        if (coffee == null) {
            throw new RuntimeException("Café no encontrado con id: " + id);
        }
        return coffee;
    }

    public Coffee createCoffee(Coffee coffee) {
        if (coffee.getNombre() == null || coffee.getPrecio() == null) {
            throw new RuntimeException("El nombre y precio del café son obligatorios.");
        }
        return coffeeRepository.save(coffee);
    }

    public Coffee updateCoffee(Long id, Coffee coffee) {
        if (!coffeeRepository.findAll().stream().anyMatch(c -> c.getId().equals(id))) {
            throw new RuntimeException("Café no encontrado con id: " + id);
        }
        coffee.setId(id);
        return coffeeRepository.save(coffee);
    }

    public Coffee patchCoffee(Long id, Coffee coffee) {
        Coffee existingCoffee = getCoffeeById(id);
        
        if (coffee.getNombre() != null) {
            existingCoffee.setNombre(coffee.getNombre());
        }
        if (coffee.getOrigen() != null) {
            existingCoffee.setOrigen(coffee.getOrigen());
        }
        if (coffee.getNivelTostado() != null) {
            existingCoffee.setNivelTostado(coffee.getNivelTostado());
        }
        if (coffee.getPrecio() != null) {
            existingCoffee.setPrecio(coffee.getPrecio());
        }
        if (coffee.getCantidadStock() != null) {
            existingCoffee.setCantidadStock(coffee.getCantidadStock());
        }

        return coffeeRepository.save(existingCoffee);
    }

    public void deleteCoffee(Long id) {
        if (!coffeeRepository.findAll().stream().anyMatch(c -> c.getId().equals(id))) {
            throw new RuntimeException("Café no encontrado con id: " + id);
        }
        coffeeRepository.deleteById(id);
    }
}
