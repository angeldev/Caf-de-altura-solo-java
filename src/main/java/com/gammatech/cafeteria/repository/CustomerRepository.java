package com.gammatech.cafeteria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gammatech.cafeteria.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
