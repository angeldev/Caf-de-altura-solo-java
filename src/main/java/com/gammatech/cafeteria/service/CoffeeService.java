package com.gammatech.cafeteria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gammatech.cafeteria.model.Coffee;
import com.gammatech.cafeteria.repository.CoffeeRepository;

/**
 * Servicio para gestionar las operaciones relacionadas con los cafés.
 * 
 * Este servicio implementa la lógica de negocio para crear, actualizar,
 * consultar y eliminar variedades de café, incluyendo validaciones necesarias.
 * 
 * @author Marcos Sandín
 * @version 1.0
 */
@Service
public class CoffeeService {
    @Autowired
    private CoffeeRepository coffeeRepository;

    /**
     * Obtiene todas las variedades de café del sistema.
     * 
     * @return Lista de todas las variedades de café
     * @throws RuntimeException si ocurre un error al obtener los cafés
     */
    public List<Coffee> getAllCoffees() {
        try {
            return coffeeRepository.findAll();
        } catch (Exception e) {
            System.out.println("Error al obtener todos los cafés: " + e.getMessage());
            throw new RuntimeException("Error al obtener la lista de cafés");
        }
    }

    /**
     * Obtiene todas las variedades de café de forma paginada.
     * 
     * @param pageable Configuración de la paginación
     * @return Página de cafés
     * @throws RuntimeException si ocurre un error al obtener los cafés
     */
    public Page<Coffee> getAllCoffeesPaged(Pageable pageable) {
        try {
            return coffeeRepository.findAll(pageable);
        } catch (Exception e) {
            System.out.println("Error al obtener cafés paginados: " + e.getMessage());
            throw new RuntimeException("Error al obtener la lista paginada de cafés");
        }
    }

    /**
     * Obtiene una variedad de café específica por su ID.
     * 
     * @param id Identificador del café
     * @return Café encontrado
     * @throws RuntimeException si el café no existe
     */
    public Coffee getCoffeeById(Long id) {
        try {
            return coffeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Café no encontrado con id: " + id));
        } catch (RuntimeException e) {
            System.out.println("Error al buscar café por ID: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.out.println("Error inesperado al buscar café: " + e.getMessage());
            throw new RuntimeException("Error al buscar el café");
        }
    }

    /**
     * Crea una nueva variedad de café.
     * 
     * @param coffee Datos del café a crear
     * @return Café creado con su ID asignado
     * @throws RuntimeException si los datos del café no son válidos
     */
    public Coffee createCoffee(Coffee coffee) {
        try {
            validateCoffee(coffee);
            return coffeeRepository.save(coffee);
        } catch (RuntimeException e) {
            System.out.println("Error de validación al crear café: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.out.println("Error inesperado al crear café: " + e.getMessage());
            throw new RuntimeException("Error al crear el café");
        }
    }

    /**
     * Actualiza una variedad de café existente.
     * 
     * @param id Identificador del café a actualizar
     * @param coffee Nuevos datos del café
     * @return Café actualizado
     * @throws RuntimeException si el café no existe o los datos no son válidos
     */
    public Coffee updateCoffee(Long id, Coffee coffee) {
        try {
            if (!coffeeRepository.existsById(id)) {
                throw new RuntimeException("Café no encontrado con id: " + id);
            }
            validateCoffee(coffee);
            coffee.setId(id);
            return coffeeRepository.save(coffee);
        } catch (RuntimeException e) {
            System.out.println("Error al actualizar café: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.out.println("Error inesperado al actualizar café: " + e.getMessage());
            throw new RuntimeException("Error al actualizar el café");
        }
    }

    /**
     * Actualiza parcialmente una variedad de café existente.
     * Solo actualiza los campos que no son nulos en el objeto proporcionado.
     * 
     * @param id Identificador del café a actualizar
     * @param coffee Datos parciales del café a actualizar
     * @return Café actualizado
     * @throws RuntimeException si el café no existe
     */
    public Coffee patchCoffee(Long id, Coffee coffee) {
        Coffee existingCoffee = getCoffeeById(id);
        
        if (coffee.getName() != null && !coffee.getName().trim().isEmpty()) {
            existingCoffee.setName(coffee.getName());
        }
        if (coffee.getOrigin() != null && !coffee.getOrigin().trim().isEmpty()) {
            existingCoffee.setOrigin(coffee.getOrigin());
        }
        if (coffee.getRoastLevel() != null && !coffee.getRoastLevel().trim().isEmpty()) {
            existingCoffee.setRoastLevel(coffee.getRoastLevel());
        }
        if (coffee.getPrice() != null && coffee.getPrice() > 0) {
            existingCoffee.setPrice(coffee.getPrice());
        }
        if (coffee.getStockQuantity() != null && coffee.getStockQuantity() >= 0) {
            existingCoffee.setStockQuantity(coffee.getStockQuantity());
        }

        return coffeeRepository.save(existingCoffee);
    }

    /**
     * Elimina una variedad de café existente.
     * 
     * @param id Identificador del café a eliminar
     * @throws RuntimeException si el café no existe
     */
    public void deleteCoffee(Long id) {
        try {
            if (!coffeeRepository.existsById(id)) {
                throw new RuntimeException("Café no encontrado con id: " + id);
            }
            coffeeRepository.deleteById(id);
        } catch (RuntimeException e) {
            System.out.println("Error al eliminar café: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.out.println("Error inesperado al eliminar café: " + e.getMessage());
            throw new RuntimeException("Error al eliminar el café");
        }
    }

    /**
     * Verifica si existe una variedad de café con el ID especificado.
     * 
     * @param id Identificador del café a verificar
     * @return true si existe, false en caso contrario
     * @throws RuntimeException si ocurre un error al verificar
     */
    public boolean existsById(Long id) {
        try {
            return coffeeRepository.existsById(id);
        } catch (Exception e) {
            System.out.println("Error al verificar existencia de café: " + e.getMessage());
            throw new RuntimeException("Error al verificar la existencia del café");
        }
    }

    /**
     * Valida los datos de un café antes de crearlo o actualizarlo.
     * 
     * @param coffee Café a validar
     * @throws RuntimeException si el café no cumple con las validaciones
     */
    private void validateCoffee(Coffee coffee) {
        try {
            if (coffee.getName() == null || coffee.getName().trim().isEmpty()) {
                throw new RuntimeException("El nombre del café es obligatorio");
            }

            if (coffee.getOrigin() == null || coffee.getOrigin().trim().isEmpty()) {
                throw new RuntimeException("El origen del café es obligatorio");
            }

            if (coffee.getRoastLevel() == null || coffee.getRoastLevel().trim().isEmpty()) {
                throw new RuntimeException("El nivel de tostado del café es obligatorio");
            }

            if (coffee.getPrice() == null || coffee.getPrice() <= 0) {
                throw new RuntimeException("El precio del café debe ser mayor que 0");
            }

            if (coffee.getStockQuantity() == null || coffee.getStockQuantity() < 0) {
                throw new RuntimeException("La cantidad en stock del café debe ser 0 o mayor");
            }
        } catch (RuntimeException e) {
            System.out.println("Error de validación: " + e.getMessage());
            throw e;
        }
    }
}
