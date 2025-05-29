package com.gammatech.cafateria.service;

import com.gammatech.cafateria.model.Customer;
import com.gammatech.cafateria.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Page<Customer> getAllCustomersPaged(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + id));
    }

    public Customer createCustomer(Customer customer) {
        // Validar nombre
        if (customer.getName() == null || customer.getName().trim().isEmpty()) {
            throw new RuntimeException("El nombre del cliente es obligatorio");
        }

        // Validar email
        if (customer.getEmail() == null || customer.getEmail().trim().isEmpty()) {
            throw new RuntimeException("El email del cliente es obligatorio");
        }
        if (!customer.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new RuntimeException("Formato de email inválido");
        }

        // Validar dirección
        if (customer.getAddress() == null || customer.getAddress().trim().isEmpty()) {
            throw new RuntimeException("La dirección del cliente es obligatoria");
        }

        // Validar teléfono
        if (customer.getPhone() == null || customer.getPhone().trim().isEmpty()) {
            throw new RuntimeException("El teléfono del cliente es obligatorio");
        }

        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Long id, Customer customer) {
        if (!customerRepository.existsById(id)) {
            throw new RuntimeException("Cliente no encontrado con id: " + id);
        }

        // Validar nombre
        if (customer.getName() == null || customer.getName().trim().isEmpty()) {
            throw new RuntimeException("El nombre del cliente es obligatorio");
        }

        // Validar email
        if (customer.getEmail() == null || customer.getEmail().trim().isEmpty()) {
            throw new RuntimeException("El email del cliente es obligatorio");
        }
        if (!customer.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new RuntimeException("Formato de email inválido");
        }

        // Validar dirección
        if (customer.getAddress() == null || customer.getAddress().trim().isEmpty()) {
            throw new RuntimeException("La dirección del cliente es obligatoria");
        }

        // Validar teléfono
        if (customer.getPhone() == null || customer.getPhone().trim().isEmpty()) {
            throw new RuntimeException("El teléfono del cliente es obligatorio");
        }

        customer.setId(id);
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new RuntimeException("Cliente no encontrado con id: " + id);
        }
        customerRepository.deleteById(id);
    }
}
