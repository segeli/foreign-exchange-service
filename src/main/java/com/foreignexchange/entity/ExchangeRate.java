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
@Table(name = "exchange_rate")
public class ExchangeRate extends BaseEntity {

    @Column(name = "source")
    private String source;

    @Column(name = "source_amount")
    private BigDecimal sourceAmount;

    @Column(name = "target")
    private String target;

    @Column(name = "target_amount")
    private BigDecimal targetAmount;
}
