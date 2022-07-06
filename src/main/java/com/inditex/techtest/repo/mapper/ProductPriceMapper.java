package com.inditex.techtest.repo.mapper;

import com.inditex.techtest.repo.entity.ProductPriceEntity;
import com.inditex.techtest.service.domain.ProductPrice;

public class ProductPriceMapper {

    public ProductPrice map(ProductPriceEntity e) {
        return new ProductPrice(e.getId(), e.getBrandId(), e.getProductId(), e.getTariff(), e.getPriority(),
                e.getStartDate(), e.getEndDate(), e.getPrice());
    }

}
