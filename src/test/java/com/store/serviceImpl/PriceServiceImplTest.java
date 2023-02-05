package com.store.serviceImpl;

import com.store.dto.PriceDto;
import com.store.exception.InvalidInputDataException;
import com.store.exception.PriceNotFoundException;
import com.store.service.PriceService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest()
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PriceServiceImplTest {

    private static final Long PRODUCT_ID = 35455L;
    public static final Long BRAND_ID = 1L;

    @Autowired
    private PriceService priceService;

    @BeforeEach
    void setUp() {
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
        String priceDate = "2020-06-14-10.00";
        PriceDto pricePvp = priceService.findPricePvp(priceDate, PRODUCT_ID, BRAND_ID);
        assertEquals(pricePvp.getPrice(), new BigDecimal("35.50"));
    }

    /**
     * Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
     *
     * @throws InvalidInputDataException The InvalidInputDataException
     * @throws PriceNotFoundException    The PriceNotFoundException
     */
    @Test
    void findPricePvp_test2() throws InvalidInputDataException, PriceNotFoundException {
        String priceDate = "2020-06-14-16.00";
        PriceDto pricePvp = priceService.findPricePvp(priceDate, PRODUCT_ID, BRAND_ID);
        assertEquals(pricePvp.getPrice(), new BigDecimal("25.45"));
    }

    /**
     * Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
     *
     * @throws InvalidInputDataException The InvalidInputDataException
     * @throws PriceNotFoundException    The PriceNotFoundException
     */
    @Test
    void findPricePvp_test3() throws InvalidInputDataException, PriceNotFoundException {
        String priceDate = "2020-06-14-21.00";
        PriceDto pricePvp = priceService.findPricePvp(priceDate, PRODUCT_ID, BRAND_ID);
        assertEquals(pricePvp.getPrice(), new BigDecimal("35.50"));
    }

    /**
     * Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
     *
     * @throws InvalidInputDataException The InvalidInputDataException
     * @throws PriceNotFoundException    The PriceNotFoundException
     */
    @Test
    void findPricePvp_test4() throws InvalidInputDataException, PriceNotFoundException {
        String priceDate = "2020-06-15-10.00";
        PriceDto pricePvp = priceService.findPricePvp(priceDate, PRODUCT_ID, BRAND_ID);
        assertEquals(pricePvp.getPrice(), new BigDecimal("30.50"));
    }

    /**
     * Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)
     *
     * @throws InvalidInputDataException The InvalidInputDataException
     * @throws PriceNotFoundException    The PriceNotFoundException
     */
    @Test
    void findPricePvp_test5() throws InvalidInputDataException, PriceNotFoundException {
        String priceDate = "2020-06-16-21.00";
        PriceDto pricePvp = priceService.findPricePvp(priceDate, PRODUCT_ID, BRAND_ID);
        assertEquals(pricePvp.getPrice(), new BigDecimal("38.95"));
    }
}
