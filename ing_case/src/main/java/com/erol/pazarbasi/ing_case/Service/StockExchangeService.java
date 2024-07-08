package com.erol.pazarbasi.ing_case.Service;

import com.erol.pazarbasi.ing_case.entity.Stock;
import com.erol.pazarbasi.ing_case.entity.StockExchange;
import com.erol.pazarbasi.ing_case.exception.StockExchangeNotFoundException;
import com.erol.pazarbasi.ing_case.repository.StockExchangeRepository;
import com.erol.pazarbasi.ing_case.repository.StockRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Transactional
@Service
public class StockExchangeService {

    private StockExchangeRepository stockExchangeRepository;
    private StockRepository stockRepository;

    public StockExchangeService(StockExchangeRepository stockExchangeRepository, StockRepository stockRepository){
        this.stockExchangeRepository = stockExchangeRepository;
        this.stockRepository = stockRepository;
    }

    public StockExchange getStockExchangeByName(String name){
        Optional<StockExchange> stockExchange = stockExchangeRepository.findByName(name);
        return stockExchange.isPresent() ? stockExchange.get() : null;
    }

    public StockExchange addStockToExchange(String exchangeName, Stock stock) {
        Optional<StockExchange> exchange = stockExchangeRepository.findByName(exchangeName);
        if (exchange.isPresent()) {
            Optional<Stock> existingStock = stockRepository.findByName(stock.getName());
            if (existingStock.isEmpty()) {
                existingStock = Optional.of(stockRepository.save(stock));
            }
            exchange.get().addStock(existingStock.get());
            stockExchangeRepository.save(exchange.get());
            return exchange.get();
        }else{
            throw new StockExchangeNotFoundException();
        }
    }


    public void removeStockFromExchange(String exchangeName, Stock stock) {
        Optional<StockExchange> exchange = stockExchangeRepository.findByName(exchangeName);
        if (exchange.isPresent()) {
            Optional<Stock> stockOptional = stockRepository.findByName(stock.getName());
            if (stockOptional.isPresent()) {
                Stock foundStock = stockOptional.get();
                exchange.get().removeStock(foundStock);
                stockExchangeRepository.save(exchange.get());
            }
        }else{
            throw new StockExchangeNotFoundException();
        }
    }

}
