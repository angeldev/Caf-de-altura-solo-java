package com.gammatech.cafateria.controller;

import com.gammatech.cafateria.model.Coffee;
import com.gammatech.cafateria.service.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coffee")
public class CoffeeController {
	  @Autowired
	    private CoffeeService coffeeService;

	    @GetMapping
	    public ResponseEntity<List<Coffee>> getAllCoffees() {
	        return ResponseEntity.ok(coffeeService.getAllCoffees());
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
