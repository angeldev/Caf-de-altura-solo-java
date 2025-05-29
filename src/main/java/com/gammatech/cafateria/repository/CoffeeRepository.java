package com.gammatech.cafateria.repository;

import com.gammatech.cafateria.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
