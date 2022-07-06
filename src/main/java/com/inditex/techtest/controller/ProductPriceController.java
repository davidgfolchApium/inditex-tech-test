package com.inditex.techtest.controller;

import com.inditex.techtest.controller.dto.ProductPriceDto;
import com.inditex.techtest.controller.mapper.ProductPriceMapper;
import com.inditex.techtest.service.ProductPriceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class ProductPriceController {

    public static final String URI_PRODUCT_PRICE = "/product/price";

    private final ProductPriceService service;
    private final ProductPriceMapper mapper = new ProductPriceMapper();

    public ProductPriceController(ProductPriceService service) {
        this.service = service;
    }

    @GetMapping(URI_PRODUCT_PRICE)
    public ProductPriceDto price(@RequestParam Long brandId,
                                 @RequestParam Long productId,
                                 @RequestParam LocalDateTime dateTime) {
        return mapper.map(service.getForDate(brandId, productId, dateTime));
    }

}
