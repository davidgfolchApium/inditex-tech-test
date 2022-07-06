package com.inditex.techtest.service;

import com.inditex.techtest.repo.ProductPriceRepository;
import com.inditex.techtest.repo.entity.ProductPriceEntity;
import com.inditex.techtest.repo.mapper.ProductPriceMapper;
import com.inditex.techtest.service.domain.ProductPrice;
import com.inditex.techtest.service.exception.ProductPriceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static java.lang.String.format;
import static java.util.Comparator.comparing;

@Service
public class ProductPriceService {

    public static final String MSG_NOT_FOUND = "No product price found for brandId=%s, productId=%s and dateTime=%s";

    private final ProductPriceRepository repo;
    private final ProductPriceMapper mapper = new ProductPriceMapper();

    public ProductPriceService(ProductPriceRepository repo) {
        this.repo = repo;
    }

    /**
     * Double disambiguation, if max priority get 2 or more prices, max price is returned
     */
    public ProductPrice getForDate(long brandId, long productId, LocalDateTime dateTime) {
        return repo.getForDate(brandId, productId, dateTime)
                .stream()
//                .peek(System.out::println)
                .max(comparing(ProductPriceEntity::getPriority)
                        .thenComparing(ProductPriceEntity::getPrice))
                .map(mapper::map)
                .orElseThrow(() -> new ProductPriceNotFoundException(format(MSG_NOT_FOUND, brandId, productId, dateTime)));
    }

}
