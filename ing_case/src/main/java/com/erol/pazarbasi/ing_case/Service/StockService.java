package com.erol.pazarbasi.ing_case.Service;

import com.erol.pazarbasi.ing_case.entity.Stock;
import com.erol.pazarbasi.ing_case.entity.StockExchange;
import com.erol.pazarbasi.ing_case.exception.StockNotFoundException;
import com.erol.pazarbasi.ing_case.repository.StockRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Transactional
@Service
public class StockService {

    private StockRepository stockRepository;

    public StockService(StockRepository stockRepository){this.stockRepository = stockRepository;}


    public Stock getStockById(Long id) {
        return stockRepository.findById(id).orElse(null);
    }

    public Stock saveStock(Stock stock) {
        return stockRepository.save(stock);
    }

    public void deleteStock(Long id) {
        Stock stock = stockRepository.findById(id).orElse(null);
        if (stock != null) {
            List<StockExchange> exchanges = stock.getExchanges();
            for (StockExchange exchange : exchanges) {
               exchange.removeStock(stock);
            }
            stockRepository.delete(stock);
        }
    }

    public Stock updateStockPrice(Long id, BigDecimal newPrice) {
        Stock stock = stockRepository.findById(id)
                .orElseThrow(() -> new StockNotFoundException());

        stock.setCurrentPrice(newPrice);
        stockRepository.save(stock);
        return stock;
    }



}
