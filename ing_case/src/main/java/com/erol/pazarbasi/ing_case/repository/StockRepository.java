package com.erol.pazarbasi.ing_case.repository;

import com.erol.pazarbasi.ing_case.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {
    Optional<Stock> findByName(String name);
}

