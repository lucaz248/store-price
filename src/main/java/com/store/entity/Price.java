package com.store.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "PRICE")
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "BRAND_ID", nullable = false)
    private Integer brandId;

    @NotNull
    @Column(name = "START_DATE", nullable = false)
//    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime startDate;

    @NotNull
    @Column(name = "END_DATE", nullable = false)
//    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime endDate;

    @NotNull
    @Column(name = "PRICE_LIST", nullable = false)
    private Integer priceList;

    @NotNull
    @Column(name = "PRODUCT_ID", nullable = false)
    private Integer productId;

    @NotNull
    @Column(name = "PRIORITY", nullable = false)
    private Integer priority;

    @NotNull
    @Column(name = "PRICE", nullable = false, precision = 6, scale = 2)
    private BigDecimal price;

    @Size(max = 5)
    @NotNull
    @Column(name = "CURR", nullable = false, length = 5)
    private String curr;
}
