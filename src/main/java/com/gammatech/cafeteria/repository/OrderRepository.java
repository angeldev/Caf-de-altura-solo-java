package com.gammatech.cafeteria.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gammatech.cafeteria.model.Order;

/**
 * Repositorio para gestionar el acceso a datos de las órdenes.
 * 
 * Este repositorio proporciona métodos para realizar operaciones CRUD
 * (Crear, Leer, Actualizar, Eliminar) sobre las órdenes en la base de datos.
 * 
 * @author Marcos Sandín
 * @version 1.0
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    /**
     * Busca todas las órdenes asociadas a un cliente específico.
     * 
     * @param customerId Identificador del cliente
     * @return Lista de órdenes del cliente
     */
    List<Order> findByCustomerId(Long customerId);

    /**
     * Busca todas las órdenes asociadas a un cliente específico de forma paginada.
     * 
     * @param customerId Identificador del cliente
     * @param pageable Configuración de la paginación
     * @return Página de órdenes del cliente
     */
    Page<Order> findByCustomerId(Long customerId, Pageable pageable);
}
