package com.erol.pazarbasi.ing_case.repository;

import com.erol.pazarbasi.ing_case.entity.StockExchange;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockExchangeRepository extends JpaRepository<StockExchange, Long> {
    Optional<StockExchange> findByName(String name);
}