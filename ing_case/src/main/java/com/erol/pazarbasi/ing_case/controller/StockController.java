package com.erol.pazarbasi.ing_case.controller;

import com.erol.pazarbasi.ing_case.Service.StockService;
import com.erol.pazarbasi.ing_case.entity.Stock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/stock")
public class StockController {

    private StockService stockService;

    public StockController(StockService stockService){this.stockService = stockService;}

    @PostMapping
    public ResponseEntity<Stock> createStock(@RequestBody Stock stock) {
        Stock createdStock = stockService.saveStock(stock);
        return new ResponseEntity<>(createdStock, HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void deleteStock(@RequestParam Long id){
        stockService.deleteStock(id);
    }

    @PatchMapping
    public ResponseEntity<Stock> updatePrice(@RequestParam Long id, @RequestParam BigDecimal price){
        Stock stock = stockService.updateStockPrice(id, price);
        return new ResponseEntity<>(stock, HttpStatus.OK);
    }
}
