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
public class ConversionCreateRequest {

    private String sourceCurrency;

    private BigDecimal sourceAmount;

    private String targetCurrency;

    private BigDecimal targetAmount;
}
