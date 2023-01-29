package com.store.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.store.entity.Price} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PriceDto implements Serializable {
//identificador de producto, identificador de cadena, tarifa a aplicar, fechas de aplicación y precio final a aplicar.
    @JsonIgnore
    private Long id;
    @Size(min = 1)
    @NotNull
    private Integer brandId;
    @NotNull
    private LocalDateTime startDate;
    @NotNull
    private LocalDateTime endDate;
    @JsonIgnore
    @NotNull
    private Integer priceList;
    @Size(min = 1)
    @NotNull
    private Integer productId;
    @NotNull
    @JsonIgnore
    private Integer priority;
    @NotNull
    @JsonProperty("" +
            "price")
    private BigDecimal price;
    @JsonIgnore
    @Size(max = 5)
    @NotNull
    private String curr;
}