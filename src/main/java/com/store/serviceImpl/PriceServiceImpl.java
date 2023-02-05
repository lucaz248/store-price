package com.store.serviceImpl;

import com.store.consts.Consts;
import com.store.dto.PriceDto;
import com.store.exception.InvalidInputDataException;
import com.store.exception.PriceNotFoundException;
import com.store.repository.PriceRepository;
import com.store.service.PriceService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

/**
 * Pricing service
 */
@Service
public class PriceServiceImpl implements PriceService {
    private static final Logger log = LoggerFactory.getLogger(PriceServiceImpl.class);
    @Autowired
    private PriceRepository priceRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PriceDto findPricePvp(String priceDateStr, Long productId, Long brandId) throws PriceNotFoundException, InvalidInputDataException {

        validateInputData(priceDateStr, productId, brandId);
        LocalDateTime priceDate = LocalDateTime.parse(priceDateStr, Consts.DATE_TIME_FORMATTER);
        List<com.store.entity.Price> prices = priceRepository.findByParams(priceDate, productId, brandId);

        if (prices.isEmpty()) {
            throw new PriceNotFoundException();
        }

        //Si dos tarifas coinciden en un rago de fechas se aplica la de mayor prioridad (mayor valor numérico).
        prices.sort(Comparator.comparing(com.store.entity.Price::getPriority).reversed());
        com.store.entity.Price pvpPrice = prices.get(0);
        PriceDto priceDto = modelMapper.map(pvpPrice, PriceDto.class);
        return priceDto;
    }

    /**
     * Validate input data
     *
     * @param priceDateStr Price date string
     * @param productId    Product id
     * @param brandId      Brand id
     * @throws InvalidInputDataException The invalid input data exception
     */
    private void validateInputData(String priceDateStr, Long productId, Long brandId) throws InvalidInputDataException {
        boolean validData = true;
        try {
            if (productId <= 0 || brandId <= 0) {
                validData = false;
            }
            LocalDateTime priceDate = LocalDateTime.parse(priceDateStr, Consts.DATE_TIME_FORMATTER);
        } catch (Exception e) {
            validData = false;
        } finally {
            log.info("Throw InvalidInputDataFormatException");
        }

        if (!validData) {
            log.info("Throw InvalidInputDataFormatException");
            throw new InvalidInputDataException();
        }
    }
}
