package com.foreignexchange;

import com.foreignexchange.service.ExchangeRateCapabilityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ForeignExchangeServiceApplication implements CommandLineRunner {

    private final ExchangeRateCapabilityService exchangeRateCapabilityService;

    public ForeignExchangeServiceApplication(ExchangeRateCapabilityService exchangeRateCapabilityService) {
        this.exchangeRateCapabilityService = exchangeRateCapabilityService;
    }

    public static void main(String[] args) {
        SpringApplication.run(ForeignExchangeServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        exchangeRateCapabilityService.deleteAllAndBulkCreate();
    }
}
