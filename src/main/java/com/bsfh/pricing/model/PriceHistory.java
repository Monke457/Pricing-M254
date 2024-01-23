package com.bsfh.pricing.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PriceHistory implements DBEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private Product product;
    private LocalDateTime date;
    private BigDecimal price;

    public PriceHistory(Product product, LocalDateTime date, BigDecimal price) {
        this.product = product;
        this.date = date;
        this.price = price;
    }
}
