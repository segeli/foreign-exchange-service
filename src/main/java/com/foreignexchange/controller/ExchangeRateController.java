package com.foreignexchange.controller;

import com.foreignexchange.exception.InvalidFormatError;
import com.foreignexchange.exception.InvalidFormatException;
import com.foreignexchange.service.ExchangeRateReadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ExchangeRateController.ENDPOINT)
@RequiredArgsConstructor
@Tag(name = "Exchange Rate API")
public class ExchangeRateController {

    public static final String ENDPOINT = "exchange-rates";

    private final ExchangeRateReadService exchangeRateReadService;

    @Operation(summary = "Get exchange rate", description = "Retrieves the exchange rate for the specified currency pair")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = Double.class))),
            @ApiResponse(responseCode = "400", description = "Invalid currency pair format", content = @Content(schema = @Schema(implementation = InvalidFormatError.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{currencyPair}")
    public double getExchangeRate(
            @Parameter(description = "Currency pair in the format 'source-target'", example = "USD-EUR")
            @PathVariable("currencyPair") String currencyPair
    ) throws InvalidFormatException {
        return exchangeRateReadService.getExchangeRate(currencyPair);
    }
}
