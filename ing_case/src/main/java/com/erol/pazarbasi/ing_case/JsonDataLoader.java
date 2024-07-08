package com.erol.pazarbasi.ing_case;

import com.erol.pazarbasi.ing_case.repository.StockExchangeRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class JsonDataLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(JsonDataLoader.class);
    private final ObjectMapper objectMapper;
    private final StockExchangeRepository stockExchangeRepository;

    public JsonDataLoader(ObjectMapper objectMapper, StockExchangeRepository stockExchangeRepository) {
        this.objectMapper = objectMapper;
        this.stockExchangeRepository = stockExchangeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if(stockExchangeRepository.count() == 0) {
            try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/StockExchanges.json")) {
                StockExchanges stockExchanges = objectMapper.readValue(inputStream, StockExchanges.class);
                log.info("Reading {} stock exchanges from JSON data and saving to database.", stockExchanges.stockExchangeList().size());
                stockExchangeRepository.saveAll(stockExchanges.stockExchangeList());
            } catch (IOException e) {
                throw new RuntimeException("Failed to read JSON data", e);
            }
        } else {
            log.info("Not loading stock exchanges from JSON data because the collection contains data.");
        }
    }
}
