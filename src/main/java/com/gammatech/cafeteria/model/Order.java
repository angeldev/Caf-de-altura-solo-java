package com.gammatech.cafeteria.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa una orden de café en el sistema.
 * 
 * Esta clase almacena la información de una orden, incluyendo el cliente,
 * los items ordenados, el total y la fecha de creación.
 * 
 * @author Marcos Sandín
 * @version 1.0
 */
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    private double total;
    private LocalDateTime createdAt;

    /**
     * Constructor por defecto.
     */
    public Order() {
    }

    /**
     * Constructor con parámetros.
     * 
     * @param customer Cliente que realiza la orden
     * @param items Lista de items en la orden
     */
    public Order(Customer customer, List<OrderItem> items) {
        this.customer = customer;
        this.items = items;
        calculateTotal();
    }

    /**
     * Obtiene el ID de la orden.
     * 
     * @return ID de la orden
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID de la orden.
     * 
     * @param id ID de la orden
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el cliente de la orden.
     * 
     * @return Cliente de la orden
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Establece el cliente de la orden.
     * 
     * @param customer Cliente de la orden
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Obtiene los items de la orden.
     * 
     * @return Lista de items de la orden
     */
    public List<OrderItem> getItems() {
        return items;
    }

    /**
     * Establece los items de la orden.
     * 
     * @param items Lista de items de la orden
     */
    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    /**
     * Obtiene el total de la orden.
     * 
     * @return Total de la orden
     */
    public double getTotal() {
        return total;
    }

    /**
     * Establece el total de la orden.
     * 
     * @param total Total de la orden
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * Obtiene la fecha de creación de la orden.
     * 
     * @return Fecha de creación
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Establece la fecha de creación de la orden.
     * 
     * @param createdAt Fecha de creación
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Calcula el total de la orden basado en los items.
     * Este método suma el precio de cada item multiplicado por su cantidad.
     */
    public void calculateTotal() {
        this.total = items.stream()
                .mapToDouble(item -> item.getCoffee().getPrice() * item.getQuantity())
                .sum();
    }
}

