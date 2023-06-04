package com.foreignexchange.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "conversion")
public class Conversion extends BaseEntity {

    @Column(name = "source_currency")
    private String sourceCurrency;

    @Column(name = "source_amount")
    private BigDecimal sourceAmount;

    @Column(name = "target_currency")
    private String targetCurrency;

    @Column(name = "target_amount")
    private BigDecimal targetAmount;
}
