package com.store.repository;

import com.store.entity.Price;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static com.store.consts.Consts.DATE_TIME_FORMATTER;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PriceRepositoryTest {
    private static final Long PRODUCT_ID = 35455L;
    public static final Long BRAND_ID = 1L;
    @Autowired
    private PriceRepository priceRepository;

    @Test
    void findByParams() {

        String datePriceNow = "2020-06-14-10.00";
        LocalDateTime priceDate = LocalDateTime.parse(datePriceNow, DATE_TIME_FORMATTER);

        //        Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
        List<Price> priceByParams = priceRepository.findByParams(priceDate, PRODUCT_ID, BRAND_ID);
        assertEquals(priceByParams.size(), 1);

        //        Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
        datePriceNow = "2020-06-14-16.00";
        priceDate = LocalDateTime.parse(datePriceNow, DATE_TIME_FORMATTER);
        List<Price> priceByParams2 = priceRepository.findByParams(priceDate, PRODUCT_ID, BRAND_ID);
        assertEquals(priceByParams2.size(), 2);

        //        Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
        datePriceNow = "2020-06-14-21.00";
        priceDate = LocalDateTime.parse(datePriceNow, DATE_TIME_FORMATTER);
        List<Price> priceByParams3 = priceRepository.findByParams(priceDate, PRODUCT_ID, BRAND_ID);
        assertEquals(priceByParams3.size(), 1);

        //        Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
        datePriceNow = "2020-06-15-10.00";
        priceDate = LocalDateTime.parse(datePriceNow, DATE_TIME_FORMATTER);
        List<Price> priceByParams4 = priceRepository.findByParams(priceDate, PRODUCT_ID, BRAND_ID);
        assertEquals(priceByParams4.size(), 2);

        //        Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)
        datePriceNow = "2020-06-16-21.00";
        priceDate = LocalDateTime.parse(datePriceNow, DATE_TIME_FORMATTER);
        List<Price> priceByParams5 = priceRepository.findByParams(priceDate, PRODUCT_ID, BRAND_ID);
        assertEquals(priceByParams5.size(), 2);
    }
}
