package com.foreignexchange.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConversionConvertRequest {

    private BigDecimal sourceAmount;

    private String sourceCurrency;

    private String targetCurrency;
}
