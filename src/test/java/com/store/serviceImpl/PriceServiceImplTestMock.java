package com.store.serviceImpl;

import com.store.dto.PriceDto;
import com.store.entity.Brand;
import com.store.entity.Price;
import com.store.exception.InvalidInputDataException;
import com.store.exception.PriceNotFoundException;
import com.store.repository.PriceRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.store.consts.Consts.DATE_TIME_FORMATTER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class PriceServiceImplTestMock {
    private static final Long PRODUCT_ID = 35455L;
    public static final Long BRAND_ID = 1L;
    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private PriceServiceImpl priceService;

    @Spy
    private ModelMapper modelMapper;

    private Price price;
    private List<Price> prices;


    @BeforeEach
    void setUp() {
        Brand brand = new Brand();
        brand.setId(1L);
        brand.setName("Brand 1");
        brand.setDescription("Brand 1 description");
        brand.setPrices(new ArrayList<>());

        price = Price.builder()
                .id(1L)
                .productId(PRODUCT_ID)
                .brand(brand)
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
                .priceList(1)
                .priority(1)
                .price(new BigDecimal("35.50"))
                .curr("EUR")
                .build();

        prices = new ArrayList<>();
        prices.add(price);
    }

    @AfterEach
    void tearDown() {
    }

    /**
     * Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
     *
     * @throws InvalidInputDataException The InvalidInputDataException
     * @throws PriceNotFoundException    The PriceNotFoundException
     */
    @Test
    void findPricePvp_test1() throws InvalidInputDataException, PriceNotFoundException {
        String priceDateStr = "2020-06-14-10.00";
        LocalDateTime pricedate = LocalDateTime.parse(priceDateStr, DATE_TIME_FORMATTER);

        BDDMockito.given(priceRepository.findByParams(pricedate, PRODUCT_ID, BRAND_ID))
                .willReturn(prices);

        PriceDto pricePvp = priceService.findPricePvp(priceDateStr, PRODUCT_ID, BRAND_ID);
        assertNotNull(pricePvp);
        assertEquals(new BigDecimal("35.50"), price.getPrice());
    }

    /**
     * Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
     *
     * @throws InvalidInputDataException The InvalidInputDataException
     * @throws PriceNotFoundException    The PriceNotFoundException
     */
    @Test
    void findPricePvp_test2() throws InvalidInputDataException, PriceNotFoundException {
        BigDecimal datePrice = new BigDecimal("25.45");
        this.prices.get(0).setPrice(datePrice);
        String priceDateStr = "2020-06-14-16.00";
        LocalDateTime pricedate = LocalDateTime.parse(priceDateStr, DATE_TIME_FORMATTER);

        BDDMockito.given(priceRepository.findByParams(pricedate, PRODUCT_ID, BRAND_ID))
                .willReturn(prices);

        PriceDto pricePvp = priceService.findPricePvp(priceDateStr, PRODUCT_ID, BRAND_ID);
        assertNotNull(pricePvp);
        assertEquals(pricePvp.getPrice(), datePrice);
    }

    /**
     * Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
     *
     * @throws InvalidInputDataException The InvalidInputDataException
     * @throws PriceNotFoundException    The PriceNotFoundException
     */
    @Test
    void findPricePvp_test3() throws InvalidInputDataException, PriceNotFoundException {
        BigDecimal datePrice = new BigDecimal("35.50");
        this.prices.get(0).setPrice(datePrice);
        String priceDateStr = "2020-06-14-21.00";
        LocalDateTime pricedate = LocalDateTime.parse(priceDateStr, DATE_TIME_FORMATTER);

        BDDMockito.given(priceRepository.findByParams(pricedate, PRODUCT_ID, BRAND_ID))
                .willReturn(prices);

        PriceDto pricePvp = priceService.findPricePvp(priceDateStr, PRODUCT_ID, BRAND_ID);
        assertNotNull(pricePvp);
        assertEquals(pricePvp.getPrice(), datePrice);
    }

    /**
     * Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
     *
     * @throws InvalidInputDataException The InvalidInputDataException
     * @throws PriceNotFoundException    The PriceNotFoundException
     */
    @Test
    void findPricePvp_test4() throws InvalidInputDataException, PriceNotFoundException {
        BigDecimal datePrice = new BigDecimal("30.50");
        this.prices.get(0).setPrice(datePrice);

        String priceDateStr = "2020-06-15-10.00";
        LocalDateTime pricedate = LocalDateTime.parse(priceDateStr, DATE_TIME_FORMATTER);

        BDDMockito.given(priceRepository.findByParams(pricedate, PRODUCT_ID, BRAND_ID))
                .willReturn(prices);

        PriceDto pricePvp = priceService.findPricePvp(priceDateStr, PRODUCT_ID, BRAND_ID);
        assertNotNull(pricePvp);
        assertEquals(pricePvp.getPrice(), datePrice);
    }

    /**
     * Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)
     *
     * @throws InvalidInputDataException The InvalidInputDataException
     * @throws PriceNotFoundException    The PriceNotFoundException
     */
    @Test
    void findPricePvp_test5() throws InvalidInputDataException, PriceNotFoundException {
        BigDecimal datePrice = new BigDecimal("38.95");
        this.prices.get(0).setPrice(datePrice);

        String priceDateStr = "2020-06-16-21.00";
        LocalDateTime pricedate = LocalDateTime.parse(priceDateStr, DATE_TIME_FORMATTER);

        BDDMockito.given(priceRepository.findByParams(pricedate, PRODUCT_ID, BRAND_ID))
                .willReturn(prices);

        PriceDto pricePvp = priceService.findPricePvp(priceDateStr, PRODUCT_ID, BRAND_ID);
        assertNotNull(pricePvp);
        assertEquals(pricePvp.getPrice(), datePrice);
    }
}