package com.gammatech.cafeteria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gammatech.cafeteria.model.Coffee;

/**
 * Repositorio para gestionar el acceso a datos de los cafés.
 * 
 * Este repositorio proporciona métodos para realizar operaciones CRUD
 * (Crear, Leer, Actualizar, Eliminar) sobre las variedades de café en la base de datos.
 * 
 * @author Marcos Sandín
 * @version 1.0
 */
@Repository
public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}

