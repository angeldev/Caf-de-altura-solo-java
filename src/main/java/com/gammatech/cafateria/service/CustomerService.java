package com.gammatech.cafateria.service;

import com.gammatech.cafateria.model.Customer;
import com.gammatech.cafateria.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomerService {
	 @Autowired
	    private CustomerRepository customerRepository;

	    public List<Customer> getAllCustomers() {
	        return customerRepository.findAll();
	    }

	    public Customer getCustomerById(Long id) {
	        Customer customer = customerRepository.findById(id);
	        if (customer == null) {
	            throw new RuntimeException("Cliente no encontrado con id: " + id);
	        }
	        return customer;
	    }

	    public Customer createCustomer(Customer customer) {
	        if (customer.getNombre() == null || customer.getNombre().trim().isEmpty()) {
	            throw new RuntimeException("El nombre del cliente es obligatorio.");
	        }
	        return customerRepository.save(customer);
	    }

	    public Customer updateCustomer(Long id, Customer customer) {
	        if (!customerRepository.findAll().stream().anyMatch(c -> c.getId().equals(id))) {
	            throw new RuntimeException("Cliente no encontrado con id: " + id);
	        }
	        customer.setId(id);
	        return customerRepository.save(customer);
	    }

	    public void deleteCustomer(Long id) {
	        if (!customerRepository.findAll().stream().anyMatch(c -> c.getId().equals(id))) {
	            throw new RuntimeException("Cliente no encontrado con id: " + id);
	        }
	        customerRepository.deleteById(id);
	    }
}
