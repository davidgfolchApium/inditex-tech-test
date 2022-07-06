package com.inditex.techtest.controller.dto;

import java.time.LocalDateTime;

public record ProductPriceDto(Long id,
                              Long brandId,
                              Long productId,
                              Long tariff,
                              Long priority,
                              LocalDateTime startDate,
                              LocalDateTime endDate,
                              double price) {
}
