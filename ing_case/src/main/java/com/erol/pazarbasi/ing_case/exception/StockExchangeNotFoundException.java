package com.erol.pazarbasi.ing_case.exception;

public class StockExchangeNotFoundException extends RuntimeException {
    public StockExchangeNotFoundException() {
        super("Stock Exchange Not Found");
    }
}
