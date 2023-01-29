package com.store.repository;

import com.store.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The Price repository.
 */
public interface PriceRepository extends JpaRepository<Price, Long> {

    /**
     * Find price by params.
     *
     * @param priceDate The price date.
     * @param productId The product id.
     * @param brandId   The brand id.
     * @return The resulted price list.
     */
    @Query("SELECT p FROM Price p WHERE (p.startDate<=:priceDate AND p.endDate>=:priceDate) AND p.productId=:productId AND p.brandId=:brandId")
    List<Price> findByParams(@Param("priceDate") LocalDateTime priceDate, @Param("productId") Integer productId, @Param("brandId") Integer brandId);
}