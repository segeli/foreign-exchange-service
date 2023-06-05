package com.foreignexchange.controller;

import com.foreignexchange.exception.CurrencyPairNotFoundException;
import com.foreignexchange.model.ConversionConvertRequest;
import com.foreignexchange.model.ConversionConvertResponse;
import com.foreignexchange.service.ConversionCapabilityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ConversionCapabilityController.ENDPOINT)
@RequiredArgsConstructor
@Tag(name = "Conversion Capability API")
public class ConversionCapabilityController {

    public static final String ENDPOINT = "conversion-capability";

    private final ConversionCapabilityService conversionCapabilityService;

    @Operation(
            summary = "Convert Currency",
            description = "Converts the source amount from the source currency to the target currency"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = ConversionConvertResponse.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    })
    @PostMapping(value = "/convert", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ConversionConvertResponse convert(@RequestBody ConversionConvertRequest request
    ) throws CurrencyPairNotFoundException {
        return conversionCapabilityService.convert(request);
    }
}
