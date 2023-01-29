package com.store.repository;

import com.store.entity.Price;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PriceRepositoryTest {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm");
    private static final int PRODUCT_ID = 35455;
    public static final int BRAND_ID = 1;
    @Autowired
    private PriceRepository priceRepository;

    @Test
    void findByParams() {
        String datePriceNow = "2020-06-14-10.00";
        LocalDateTime priceDate = LocalDateTime.parse(datePriceNow, DATE_TIME_FORMATTER);

        //        Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
        List<Price> priceByParams = priceRepository.findByParams(priceDate, PRODUCT_ID, BRAND_ID);
        Assertions.assertTrue(priceByParams.size() == 1);

        //        Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
        datePriceNow = "2020-06-14-16.00";
        priceDate = LocalDateTime.parse(datePriceNow, DATE_TIME_FORMATTER);
        List<Price> priceByParams2 = priceRepository.findByParams(priceDate, PRODUCT_ID, BRAND_ID);
        Assertions.assertTrue(priceByParams2.size() == 2);

        //        Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
        datePriceNow = "2020-06-14-21.00";
        priceDate = LocalDateTime.parse(datePriceNow, DATE_TIME_FORMATTER);
        List<Price> priceByParams3 = priceRepository.findByParams(priceDate, PRODUCT_ID, BRAND_ID);
        Assertions.assertTrue(priceByParams3.size() == 1);

        //        Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
        datePriceNow = "2020-06-15-10.00";
        priceDate = LocalDateTime.parse(datePriceNow, DATE_TIME_FORMATTER);
        List<Price> priceByParams4 = priceRepository.findByParams(priceDate, PRODUCT_ID, BRAND_ID);
        Assertions.assertTrue(priceByParams4.size() == 2);

        //        Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)
        datePriceNow = "2020-06-16-21.00";
        priceDate = LocalDateTime.parse(datePriceNow, DATE_TIME_FORMATTER);
        List<Price> priceByParams5 = priceRepository.findByParams(priceDate, PRODUCT_ID, BRAND_ID);
        Assertions.assertTrue(priceByParams5.size() == 2);
    }
}
