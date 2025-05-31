package com.gammatech.cafeteria.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un café en el sistema.
 * 
 * Esta clase almacena la información de cada variedad de café,
 * incluyendo su nombre, origen, nivel de tostado, precio y cantidad en stock.
 * 
 * @author Marcos Sandín
 * @version 1.0
 */
@Entity
@Table(name = "coffee")
public class Coffee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String origin;

    @Column(name = "roast_level", nullable = false)
    private String roastLevel;

    @Column(nullable = false)
    private Double price;

    @Column(name = "stock_quantity", nullable = false)
    private Integer stockQuantity;

    @OneToMany(mappedBy = "coffee", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    /**
     * Obtiene el ID del café.
     * 
     * @return ID del café
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID del café.
     * 
     * @param id ID del café
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del café.
     * 
     * @return Nombre del café
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre del café.
     * 
     * @param name Nombre del café
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene el origen del café.
     * 
     * @return Origen del café
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Establece el origen del café.
     * 
     * @param origin Origen del café
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    /**
     * Obtiene el nivel de tostado del café.
     * 
     * @return Nivel de tostado del café
     */
    public String getRoastLevel() {
        return roastLevel;
    }

    /**
     * Establece el nivel de tostado del café.
     * 
     * @param roastLevel Nivel de tostado del café
     */
    public void setRoastLevel(String roastLevel) {
        this.roastLevel = roastLevel;
    }

    /**
     * Obtiene el precio del café.
     * 
     * @return Precio del café
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Establece el precio del café.
     * 
     * @param price Precio del café
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Obtiene la cantidad en stock del café.
     * 
     * @return Cantidad en stock
     */
    public Integer getStockQuantity() {
        return stockQuantity;
    }

    /**
     * Establece la cantidad en stock del café.
     * 
     * @param stockQuantity Cantidad en stock
     */
    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    /**
     * Obtiene la lista de items de orden asociados a este café.
     * 
     * @return Lista de items de orden
     */
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    /**
     * Establece la lista de items de orden asociados a este café.
     * 
     * @param orderItems Lista de items de orden
     */
    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}

