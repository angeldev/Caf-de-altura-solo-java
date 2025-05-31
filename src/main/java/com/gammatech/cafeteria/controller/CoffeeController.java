package com.gammatech.cafeteria.controller;

import com.gammatech.cafeteria.model.Coffee;
import com.gammatech.cafeteria.service.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**   
 * Controlador REST para gestionar las operaciones relacionadas con los cafés.
 * 
 * Este controlador maneja todas las peticiones HTTP relacionadas con los cafés,
 * incluyendo la creación, consulta, actualización y eliminación de variedades de café.
 * 
 * @author Marcos Sandín
 * @version 1.0
 */
@RestController
@RequestMapping("/coffee")
public class CoffeeController {
    @Autowired
    private CoffeeService coffeeService;

    /**
     * Obtiene todas las variedades de café de forma ordenada.
     * 
     * @param pageable Configuración del orden.
     * @return Lista ordenada de cafés.
     */
    @GetMapping
    public ResponseEntity<?> getAllCoffees(Pageable pageable) {
        try {
            Page<Coffee> coffees = coffeeService.getAllCoffeesPaged(pageable);
            return ResponseEntity.ok(coffees);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Error: No se pudo obtener la lista de cafés. " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Obtiene una variedad de café específica por su ID.
     * 
     * @param id Identificador del café
     * @return Café encontrado
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getCoffeeById(@PathVariable Long id) {
        try {
            Coffee coffee = coffeeService.getCoffeeById(id);
            return ResponseEntity.ok(coffee);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Error: Café no encontrado. " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Crea una nueva variedad de café.
     * 
     * @param coffee Datos del café a crear.
     * @return Café creado
     */
    @PostMapping
    public ResponseEntity<?> createCoffee(@RequestBody Coffee coffee) {
        try {
            Coffee createdCoffee = coffeeService.createCoffee(coffee);
            return new ResponseEntity<>(createdCoffee, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Error: No se pudo crear el café. " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Actualiza una variedad de café existente.
     * 
     * @param id Identificador del café a actualizar
     * @param coffee Nuevos datos del café
     * @return Café actualizado
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCoffee(@PathVariable Long id, @RequestBody Coffee coffee) {
        try {
            Coffee updatedCoffee = coffeeService.updateCoffee(id, coffee);
            return ResponseEntity.ok(updatedCoffee);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Error: No se pudo actualizar el café. " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Actualiza parcialmente una variedad de café existente.
     * 
     * @param id Identificador del café a actualizar
     * @param coffee Datos parciales del café a actualizar
     * @return Café actualizado
     */
    @PatchMapping("/{id}")
    public ResponseEntity<?> patchCoffee(@PathVariable Long id, @RequestBody Coffee coffee) {
        try {
            Coffee patchedCoffee = coffeeService.patchCoffee(id, coffee);
            return ResponseEntity.ok(patchedCoffee);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Error: No se pudo modificar el café. " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Elimina una variedad de café existente.
     * 
     * @param id Identificador del café a eliminar
     * @return Respuesta vacía con estado OK si se eliminó correctamente
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCoffee(@PathVariable Long id) {
        try {
            coffeeService.deleteCoffee(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Error: No se pudo eliminar el café. " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}