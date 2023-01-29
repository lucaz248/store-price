package com.store;

import com.store.serviceImpl.PriceServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StorePricingApplicationTests {

    @Autowired
    private PriceServiceImpl priceService;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(priceService);
    }

}
