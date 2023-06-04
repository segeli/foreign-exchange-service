package com.foreignexchange.client;

import com.foreignexchange.client.config.ForeignExchangeClientConfiguration;
import com.foreignexchange.client.model.ExchangeRateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(
        name = "foreign-exchange-client",
        url = "http://data.fixer.io/api",
        configuration = ForeignExchangeClientConfiguration.class
)
public interface ForeignExchangeClient {

    @RequestMapping(method = RequestMethod.GET, value = "/latest?access_key=${fiver.api-key}")
    ExchangeRateResponse getExchangeRates();

}
