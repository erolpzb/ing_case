package com.erol.pazarbasi.ing_case.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class StockExchange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    private String description;
    private boolean liveInMarket;


    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "stock_exchange_stock",
            joinColumns = { @JoinColumn(name = "stock_exchange_id") },
            inverseJoinColumns = { @JoinColumn(name = "stock_id") }
    )
    private Set<Stock> stocks = new HashSet<>();

    public StockExchange() {}

    public StockExchange(String name, String description) {
        this.name = name;
        this.description = description;
        this.liveInMarket = false;
    }


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

    public boolean isLiveInMarket() {
        return liveInMarket;
    }

    public void setLiveInMarket(boolean liveInMarket) {
        this.liveInMarket = liveInMarket;
    }

    public Set<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(Set<Stock> stocks) {
        this.stocks = stocks;
    }

    public void addStock(Stock stock) {
        this.stocks.add(stock);
        stock.getExchanges().add(this);
        updateLiveInMarketStatus();
    }

    public void removeStock(Stock stock) {
        this.stocks.remove(stock);
        this.updateLiveInMarketStatus();
    }

    private void updateLiveInMarketStatus() {
        if (stocks.size() >= 5) {
            this.liveInMarket = true;
        } else {
            this.liveInMarket = false;
        }
    }
}
