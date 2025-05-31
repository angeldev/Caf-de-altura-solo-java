package com.gammatech.cafeteria.model;

import jakarta.persistence.*;

/**
 * Clase que representa un item individual dentro de una orden.
 * 
 * Esta clase almacena la información de cada producto (café) ordenado,
 * incluyendo la cantidad y la referencia a la orden y al café.
 * 
 * @author Marcos Sandín
 * @version 1.0
 */
@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coffee_id", nullable = false)
    private Coffee coffee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double price;

    /**
     * Constructor por defecto.
     */
    public OrderItem() {
    }

    /**
     * Constructor con parámetros.
     * 
     * @param order Orden a la que pertenece el item
     * @param coffee Café ordenado
     * @param quantity Cantidad ordenada
     */
    public OrderItem(Order order, Coffee coffee, Integer quantity) {
        this.order = order;
        this.coffee = coffee;
        this.quantity = quantity;
    }

    /**
     * Obtiene el ID del item.
     * 
     * @return ID del item
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID del item.
     * 
     * @param id ID del item
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene la orden a la que pertenece el item.
     * 
     * @return Orden del item
     */
    public Order getOrder() {
        return order;
    }

    /**
     * Establece la orden a la que pertenece el item.
     * 
     * @param order Orden del item
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * Obtiene el café ordenado.
     * 
     * @return Café del item
     */
    public Coffee getCoffee() {
        return coffee;
    }

    /**
     * Establece el café ordenado.
     * 
     * @param coffee Café del item
     */
    public void setCoffee(Coffee coffee) {
        this.coffee = coffee;
    }

    /**
     * Obtiene la cantidad ordenada.
     * 
     * @return Cantidad del item
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Establece la cantidad ordenada.
     * 
     * @param quantity Cantidad del item
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * Obtiene el precio unitario del item.
     * 
     * @return Precio unitario del item
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Establece el precio unitario del item.
     * 
     * @param price Precio unitario del item
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Calcula y obtiene el subtotal del item.
     * El subtotal es el resultado de multiplicar el precio unitario por la cantidad.
     * 
     * @return Subtotal del item (precio * cantidad)
     */
    public Double getSubtotal() {
        return price * quantity;
    }
}
