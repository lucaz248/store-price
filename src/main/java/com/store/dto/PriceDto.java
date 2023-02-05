package com.store.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.store.entity.Brand;
import lombok.*;

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
@Builder
@ToString
public class PriceDto implements Serializable {
    @JsonIgnore
    private Long id;

    @JsonIgnore
    private Brand brand;

    @Size(min = 1)
    @NotNull
    private Long brandId;

    @JsonProperty("unpackNested")
    private void unpackNested() {
        this.brandId = this.brand.getId();
    }

    @NotNull
    private LocalDateTime startDate;
    @NotNull
    private LocalDateTime endDate;
    @JsonIgnore
    @NotNull
    private Integer priceList;
    @Size(min = 1)
    @NotNull
    private Long productId;
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