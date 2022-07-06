package com.inditex.techtest.controller;

import com.inditex.techtest.Application;
import com.inditex.techtest.controller.base.ErrorResponse;
import com.inditex.techtest.controller.dto.ProductPriceDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.inditex.techtest.controller.ProductPriceController.URI_PRODUCT_PRICE;
import static com.inditex.techtest.service.ProductPriceService.MSG_NOT_FOUND;
import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest(classes = Application.class, webEnvironment = RANDOM_PORT)
class ProductPriceEntityControllerIT {

    private static final String URI = URI_PRODUCT_PRICE + "?brandId=1&productId=35455&dateTime=";
    private static final String SERVER = "http://localhost:";
    private static final String DATE_TIME = "2020-06-%sT%s:00:00";

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate template;

    @ParameterizedTest
    @CsvSource({"10, 14, 1, 35.50",
            "16, 14, 2, 25.45",
            "21, 14, 1, 35.50",
            "10, 15, 3, 30.5",
            "21, 16, 4, 38.95"})
    void test(int hour, int day, long tariff, double price) {
        ProductPriceDto res = getForDate(format(DATE_TIME, day, hour), ProductPriceDto.class, OK);
        assertResult(tariff, price, res);
    }

    @Test
    void notFound() {
        ErrorResponse res = getForDate(format(DATE_TIME, "01", "01"), ErrorResponse.class, NOT_FOUND);
        assertNotNull(res.now());
        assertEquals(format(MSG_NOT_FOUND, 1, 35455, "2020-06-01T01:00"), res.message());
    }

    private void assertResult(long tariff, double price, ProductPriceDto res) {
        assertEquals(1, res.brandId());
        assertEquals(35455, res.productId());
        assertEquals(price, res.price());
        assertEquals(tariff, res.tariff());
    }

    private <T> T getForDate(String date, Class<T> clazz, HttpStatus status) {
        return assertStatus(template.getForEntity(SERVER + port + URI + date, clazz), status);
    }

    private <T> T assertStatus(ResponseEntity<T> res, HttpStatus status) {
        assertEquals(status, res.getStatusCode());
        return res.getBody();
    }
}