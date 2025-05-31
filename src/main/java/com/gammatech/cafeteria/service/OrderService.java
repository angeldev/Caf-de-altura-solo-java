package com.gammatech.cafeteria.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gammatech.cafeteria.model.Order;
import com.gammatech.cafeteria.model.OrderItem;
import com.gammatech.cafeteria.repository.CoffeeRepository;
import com.gammatech.cafeteria.repository.CustomerRepository;
import com.gammatech.cafeteria.repository.OrderRepository;

/**
 * Servicio para gestionar las operaciones relacionadas con las órdenes.
 * 
 * Este servicio implementa la lógica de negocio para crear, actualizar,
 * consultar y eliminar órdenes, incluyendo validaciones y cálculos necesarios.
 * 
 * @author Marcos Sandín
 * @version 1.0
 */
@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private CoffeeRepository coffeeRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CoffeeService coffeeService;

    /**
     * Obtiene todas las órdenes del sistema.
     * 
     * @return Lista de todas las órdenes
     * @throws RuntimeException si ocurre un error al obtener las órdenes
     */
    public List<Order> getAllOrders() {
        try {
            return orderRepository.findAll();
        } catch (Exception e) {
            System.out.println("Error al obtener todas las órdenes: " + e.getMessage());
            throw new RuntimeException("Error al obtener la lista de órdenes");
        }
    }

    /**
     * Obtiene todas las órdenes de forma paginada.
     * 
     * @param pageable Configuración de la paginación
     * @return Página de órdenes
     * @throws RuntimeException si ocurre un error al obtener las órdenes
     */
    public Page<Order> getAllOrdersPaged(Pageable pageable) {
        try {
            return orderRepository.findAll(pageable);
        } catch (Exception e) {
            System.out.println("Error al obtener órdenes paginadas: " + e.getMessage());
            throw new RuntimeException("Error al obtener la lista paginada de órdenes");
        }
    }

    /**
     * Obtiene una orden específica por su ID.
     * 
     * @param id Identificador de la orden
     * @return La orden encontrada
     * @throws RuntimeException si la orden no existe
     */
    public Order getOrderById(Long id) {
        try {
            return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada con id: " + id));
        } catch (RuntimeException e) {
            System.out.println("Error al buscar orden por ID: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.out.println("Error inesperado al buscar orden: " + e.getMessage());
            throw new RuntimeException("Error al buscar la orden");
        }
    }

    /**
     * Obtiene todas las órdenes de un cliente específico.
     * 
     * @param customerId Identificador del cliente
     * @return Lista de órdenes del cliente
     * @throws RuntimeException si el cliente no existe
     */
    public List<Order> getOrdersByCustomerId(Long customerId) {
        try {
            if (!customerService.existsById(customerId)) {
                throw new RuntimeException("Cliente no encontrado con id: " + customerId);
            }
            return orderRepository.findByCustomerId(customerId);
        } catch (RuntimeException e) {
            System.out.println("Error al buscar órdenes por cliente: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.out.println("Error inesperado al buscar órdenes: " + e.getMessage());
            throw new RuntimeException("Error al buscar las órdenes del cliente");
        }
    }

    /**
     * Obtiene las órdenes de un cliente de forma paginada.
     * 
     * @param customerId Identificador del cliente
     * @param pageable Configuración de la paginación
     * @return Página de órdenes del cliente
     * @throws RuntimeException si el cliente no existe
     */
    public Page<Order> getOrdersByCustomerIdPaged(Long customerId, Pageable pageable) {
        try {
            if (!customerService.existsById(customerId)) {
                throw new RuntimeException("Cliente no encontrado con id: " + customerId);
            }
            return orderRepository.findByCustomerId(customerId, pageable);
        } catch (RuntimeException e) {
            System.out.println("Error al buscar órdenes paginadas por cliente: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.out.println("Error inesperado al buscar órdenes paginadas: " + e.getMessage());
            throw new RuntimeException("Error al buscar las órdenes paginadas del cliente");
        }
    }

    /**
     * Crea una nueva orden.
     * 
     * @param order Datos de la orden a crear
     * @return La orden creada con su ID asignado
     * @throws RuntimeException si los datos de la orden no son válidos
     */
    public Order createOrder(Order order) {
        try {
            validateOrder(order);
            order.setCreatedAt(LocalDateTime.now());
            order.calculateTotal();
            return orderRepository.save(order);
        } catch (RuntimeException e) {
            System.out.println("Error de validación al crear orden: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.out.println("Error inesperado al crear orden: " + e.getMessage());
            throw new RuntimeException("Error al crear la orden");
        }
    }

    /**
     * Actualiza una orden existente.
     * 
     * @param id Identificador de la orden a actualizar
     * @param order Nuevos datos de la orden
     * @return La orden actualizada
     * @throws RuntimeException si la orden no existe o los datos no son válidos
     */
    public Order updateOrder(Long id, Order order) {
        try {
            if (!orderRepository.existsById(id)) {
                throw new RuntimeException("Orden no encontrada con id: " + id);
            }
            validateOrder(order);
            order.setId(id);
            order.calculateTotal();
            return orderRepository.save(order);
        } catch (RuntimeException e) {
            System.out.println("Error al actualizar orden: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.out.println("Error inesperado al actualizar orden: " + e.getMessage());
            throw new RuntimeException("Error al actualizar la orden");
        }
    }

    /**
     * Elimina una orden existente.
     * 
     * @param id Identificador de la orden a eliminar
     * @throws RuntimeException si la orden no existe
     */
    public void deleteOrder(Long id) {
        try {
            if (!orderRepository.existsById(id)) {
                throw new RuntimeException("Orden no encontrada con id: " + id);
            }
            orderRepository.deleteById(id);
        } catch (RuntimeException e) {
            System.out.println("Error al eliminar orden: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.out.println("Error inesperado al eliminar orden: " + e.getMessage());
            throw new RuntimeException("Error al eliminar la orden");
        }
    }

    /**
     * Valida los datos de una orden antes de crearla o actualizarla.
     * 
     * @param order Orden a validar
     * @throws RuntimeException si la orden no cumple con las validaciones
     */
    private void validateOrder(Order order) {
        try {
            if (order.getCustomer() == null || order.getCustomer().getId() == null) {
                throw new RuntimeException("El cliente es obligatorio");
            }

            if (!customerService.existsById(order.getCustomer().getId())) {
                throw new RuntimeException("Cliente no encontrado con id: " + order.getCustomer().getId());
            }

            if (order.getItems() == null || order.getItems().isEmpty()) {
                throw new RuntimeException("La orden debe tener al menos un item");
            }

            for (OrderItem item : order.getItems()) {
                if (item.getCoffee() == null || item.getCoffee().getId() == null) {
                    throw new RuntimeException("El café es obligatorio en cada item");
                }

                if (!coffeeService.existsById(item.getCoffee().getId())) {
                    throw new RuntimeException("Café no encontrado con id: " + item.getCoffee().getId());
                }

                if (item.getQuantity() <= 0) {
                    throw new RuntimeException("La cantidad debe ser mayor a 0");
                }
            }
        } catch (RuntimeException e) {
            System.out.println("Error de validación: " + e.getMessage());
            throw e;
        }
    }
}

