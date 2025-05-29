package com.gammatech.cafateria.controller;

import com.gammatech.cafateria.model.Coffee;
import com.gammatech.cafateria.service.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coffee")
public class CoffeeController {
    @Autowired
    private CoffeeService coffeeService;

    @GetMapping
    public ResponseEntity<Page<Coffee>> getAllCoffees(Pageable pageable) {
        return ResponseEntity.ok(coffeeService.getAllCoffeesPaged(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coffee> getCoffeeById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(coffeeService.getCoffeeById(id));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Coffee> createCoffee(@RequestBody Coffee coffee) {
        try {
            Coffee createdCoffee = coffeeService.createCoffee(coffee);
            return new ResponseEntity<>(createdCoffee, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Coffee> updateCoffee(@PathVariable Long id, @RequestBody Coffee coffee) {
        try {
            Coffee updatedCoffee = coffeeService.updateCoffee(id, coffee);
            return ResponseEntity.ok(updatedCoffee);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Coffee> patchCoffee(@PathVariable Long id, @RequestBody Coffee coffee) {
        try {
            Coffee patchedCoffee = coffeeService.patchCoffee(id, coffee);
            return ResponseEntity.ok(patchedCoffee);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoffee(@PathVariable Long id) {
        try {
            coffeeService.deleteCoffee(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}