package com.gammatech.cafeteria.controller;

//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gammatech.cafeteria.model.Order;
import com.gammatech.cafeteria.service.OrderService;

/**
 * Controlador REST para gestionar las órdenes de café.
 * 
 * Este controlador maneja todas las operaciones relacionadas con las órdenes,
 * como crear nuevas órdenes, consultar órdenes existentes y actualizar órdenes.
 * 
 * @author Marcos Sandín
 * @version 1.0
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * Obtiene todas las órdenes de forma paginada.
     * 
     * @param pageable Parámetros de paginación (número de página, tamaño, ordenamiento)
     * @return Lista paginada de órdenes
     */
    @GetMapping
    public ResponseEntity<Page<Order>> getAllOrders(Pageable pageable) {
        try {
            Page<Order> orders = orderService.getAllOrdersPaged(pageable);
            return ResponseEntity.ok(orders);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Obtiene una orden específica por su ID.
     * 
     * @param id Identificador único de la orden
     * @return La orden encontrada o un error 404 si no existe
     */
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        try {
            Order order = orderService.getOrderById(id);
            return ResponseEntity.ok(order);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Crea una nueva orden.
     * 
     * @param order Datos de la orden a crear
     * @return La orden creada con su ID asignado
     */
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        try {
            Order createdOrder = orderService.createOrder(order);
            return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Actualiza una orden existente.
     * 
     * @param id Identificador de la orden a actualizar
     * @param order Nuevos datos de la orden
     * @return La orden actualizada
     */
    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order order) {
        try {
            Order updatedOrder = orderService.updateOrder(id, order);
            return ResponseEntity.ok(updatedOrder);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Obtiene todas las órdenes de un cliente específico de forma paginada.
     * 
     * @param customerId Identificador del cliente
     * @param pageable Parámetros de paginación
     * @return Lista paginada de órdenes del cliente
     */
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<Page<Order>> getOrdersByCustomerId(
            @PathVariable Long customerId, Pageable pageable) {
        try {
            Page<Order> orders = orderService.getOrdersByCustomerIdPaged(customerId, pageable);
            return ResponseEntity.ok(orders);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
