package com.foreignexchange.controller;

import com.foreignexchange.exception.CurrencyPairNotFoundException;
import com.foreignexchange.model.ConversionConvertRequest;
import com.foreignexchange.model.ConversionConvertResponse;
import com.foreignexchange.service.ConversionCapabilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ConversionCapabilityController.ENDPOINT)
@RequiredArgsConstructor
public class ConversionCapabilityController {

    public static final String ENDPOINT = "conversion-capability";

    private final ConversionCapabilityService conversionCapabilityService;

    @PostMapping(value = "/convert", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ConversionConvertResponse convert(@RequestBody ConversionConvertRequest request
    ) throws CurrencyPairNotFoundException {
        return conversionCapabilityService.convert(request);
    }
}
