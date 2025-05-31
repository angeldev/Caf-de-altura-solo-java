package com.gammatech.cafeteria.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Clase que representa un cliente en el sistema.
 * 
 * Esta clase almacena la información personal del cliente,
 * incluyendo su nombre, email, dirección, teléfono y sus órdenes.
 * 
 * @author Marcos Sandín
 * @version 1.0
 */
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phone;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    /**
     * Obtiene el ID del cliente.
     * 
     * @return ID del cliente
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID del cliente.
     * 
     * @param id ID del cliente
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del cliente.
     * 
     * @return Nombre del cliente
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre del cliente.
     * 
     * @param name Nombre del cliente
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene el email del cliente.
     * 
     * @return Email del cliente
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el email del cliente.
     * 
     * @param email Email del cliente
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene la dirección del cliente.
     * 
     * @return Dirección del cliente
     */
    public String getAddress() {
        return address;
    }

    /**
     * Establece la dirección del cliente.
     * 
     * @param address Dirección del cliente
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Obtiene el teléfono del cliente.
     * 
     * @return Teléfono del cliente
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Establece el teléfono del cliente.
     * 
     * @param phone Teléfono del cliente
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Obtiene la lista de órdenes del cliente.
     * 
     * @return Lista de órdenes del cliente
     */
    public List<Order> getOrders() {
        return orders;
    }

    /**
     * Establece la lista de órdenes del cliente.
     * 
     * @param orders Lista de órdenes del cliente
     */
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
