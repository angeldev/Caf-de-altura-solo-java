package com.gammatech.cafeteria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gammatech.cafeteria.model.Customer;
import com.gammatech.cafeteria.repository.CustomerRepository;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        try {
            return customerRepository.findAll();
        } catch (Exception e) {
            System.out.println("Error al obtener todos los clientes: " + e.getMessage());
            throw new RuntimeException("Error al obtener la lista de clientes");
        }
    }

    public Page<Customer> getAllCustomersPaged(Pageable pageable) {
        try {
            return customerRepository.findAll(pageable);
        } catch (Exception e) {
            System.out.println("Error al obtener clientes paginados: " + e.getMessage());
            throw new RuntimeException("Error al obtener la lista paginada de clientes");
        }
    }

    public Customer getCustomerById(Long id) {
        try {
            return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + id));
        } catch (RuntimeException e) {
            System.out.println("Error al buscar cliente por ID: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.out.println("Error inesperado al buscar cliente: " + e.getMessage());
            throw new RuntimeException("Error al buscar el cliente");
        }
    }

    public Customer createCustomer(Customer customer) {
        try {
            validateCustomer(customer);
            return customerRepository.save(customer);
        } catch (RuntimeException e) {
            System.out.println("Error de validación al crear cliente: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.out.println("Error inesperado al crear cliente: " + e.getMessage());
            throw new RuntimeException("Error al crear el cliente");
        }
    }

    public Customer updateCustomer(Long id, Customer customer) {
        try {
            if (!customerRepository.existsById(id)) {
                throw new RuntimeException("Cliente no encontrado con id: " + id);
            }
            validateCustomer(customer);
            customer.setId(id);
            return customerRepository.save(customer);
        } catch (RuntimeException e) {
            System.out.println("Error al actualizar cliente: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.out.println("Error inesperado al actualizar cliente: " + e.getMessage());
            throw new RuntimeException("Error al actualizar el cliente");
        }
    }

    public void deleteCustomer(Long id) {
        try {
            if (!customerRepository.existsById(id)) {
                throw new RuntimeException("Cliente no encontrado con id: " + id);
            }
            customerRepository.deleteById(id);
        } catch (RuntimeException e) {
            System.out.println("Error al eliminar cliente: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.out.println("Error inesperado al eliminar cliente: " + e.getMessage());
            throw new RuntimeException("Error al eliminar el cliente");
        }
    }

    public boolean existsById(Long id) {
        try {
            return customerRepository.existsById(id);
        } catch (Exception e) {
            System.out.println("Error al verificar existencia de cliente: " + e.getMessage());
            throw new RuntimeException("Error al verificar la existencia del cliente");
        }
    }

    private void validateCustomer(Customer customer) {
        try {
            if (customer.getName() == null || customer.getName().trim().isEmpty()) {
                throw new RuntimeException("El nombre del cliente es obligatorio");
            }

            if (customer.getEmail() == null || customer.getEmail().trim().isEmpty()) {
                throw new RuntimeException("El email del cliente es obligatorio");
            }
            if (!customer.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                throw new RuntimeException("Formato de email inválido");
            }

            if (customer.getAddress() == null || customer.getAddress().trim().isEmpty()) {
                throw new RuntimeException("La dirección del cliente es obligatoria");
            }

            if (customer.getPhone() == null || customer.getPhone().trim().isEmpty()) {
                throw new RuntimeException("El teléfono del cliente es obligatorio");
            }
        } catch (RuntimeException e) {
            System.out.println("Error de validación: " + e.getMessage());
            throw e;
        }
    }
}
