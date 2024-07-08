package com.erol.pazarbasi.ing_case.controller;

import com.erol.pazarbasi.ing_case.Service.StockExchangeService;
import com.erol.pazarbasi.ing_case.entity.Stock;
import com.erol.pazarbasi.ing_case.entity.StockExchange;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/stock-exchange")
public class StockExchangeController {

    private StockExchangeService stockExchangeService;
    public StockExchangeController(StockExchangeService stockExchangeService) {
        this.stockExchangeService = stockExchangeService;
    }


    @GetMapping("/{name}")
    public ResponseEntity<StockExchange> getStockExchangeByName(@PathVariable String name) {
        StockExchange stockExchange = stockExchangeService.getStockExchangeByName(name);
        if(stockExchange == null){
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(stockExchange, HttpStatus.OK);
    }

    @PostMapping("/{name}")
    public ResponseEntity<StockExchange> addStockToExchange(@PathVariable String name, @RequestBody Stock stock) {
        StockExchange exchange = stockExchangeService.addStockToExchange(name, stock);
        if(exchange != null){
            return new ResponseEntity<>(exchange, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{name}")
    public void removeStockFromExchange(@PathVariable String name, @RequestBody Stock stock) {
        stockExchangeService.removeStockFromExchange(name, stock);
    }
}
