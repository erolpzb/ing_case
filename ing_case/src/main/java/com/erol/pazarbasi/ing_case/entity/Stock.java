package com.erol.pazarbasi.ing_case.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    private String description;
    private BigDecimal currentPrice;
    @UpdateTimestamp
    private LocalDateTime lastUpdate;

    @ManyToMany(mappedBy = "stocks")
    @JsonBackReference
    private List<StockExchange> exchanges = new ArrayList<>();

    public Stock() {}

    public Stock(String name, String description, BigDecimal currentPrice) {
        this.name = name;
        this.description = description;
        this.currentPrice = currentPrice;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<StockExchange> getExchanges() {
        return exchanges;
    }

    public void setExchanges(List<StockExchange> exchanges) {
        this.exchanges = exchanges;
    }
}
