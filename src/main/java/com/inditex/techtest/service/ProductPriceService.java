package com.inditex.techtest.service;

import com.inditex.techtest.repo.ProductPriceRepository;
import com.inditex.techtest.repo.mapper.ProductPriceMapper;
import com.inditex.techtest.service.domain.ProductPrice;
import com.inditex.techtest.service.exception.ProductPriceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static java.lang.String.format;

@Service
public class ProductPriceService {

    public static final String MSG_NOT_FOUND = "No product price found for brandId=%s, productId=%s and dateTime=%s";

    private final ProductPriceRepository repo;
    private final ProductPriceMapper mapper = new ProductPriceMapper();

    public ProductPriceService(ProductPriceRepository repo) {
        this.repo = repo;
    }

    public ProductPrice getForDate(long brandId, long productId, LocalDateTime dateTime) {
        return repo.getForDate(brandId, productId, dateTime)
                .stream().findFirst()
                .map(mapper::map)
                .orElseThrow(() -> new ProductPriceNotFoundException(format(MSG_NOT_FOUND, brandId, productId, dateTime)));
    }

}
