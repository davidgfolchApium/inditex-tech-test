package com.inditex.techtest.controller.mapper;

import com.inditex.techtest.controller.dto.ProductPriceDto;
import com.inditex.techtest.service.domain.ProductPrice;

public class ProductPriceMapper {

    public ProductPriceDto map(ProductPrice m) {
        return new ProductPriceDto(m.id(), m.brandId(), m.productId(), m.tariff(), m.priority(), m.startDateTime(), m.endDateTime(), m.price());
    }

}
