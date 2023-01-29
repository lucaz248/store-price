package com.store.service;

import com.store.dto.PriceDto;
import com.store.exception.InvalidInputDataException;
import com.store.exception.PriceNotFoundException;

/**
 * Pricing service
 */
public interface PriceService {

    /**
     * Find price (pvp) by params
     *
     * @param priceDate Price date to find
     * @param productId Price productId to find
     * @param brandId   Price brandId to find
     * @return Price
     */
    PriceDto findPricePvp(String priceDate, Integer productId, Integer brandId) throws PriceNotFoundException, InvalidInputDataException;
}
