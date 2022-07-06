package com.inditex.techtest.service.domain;

import java.time.LocalDateTime;

public record ProductPrice(long id,long brandId,
                           long productId,
                           long tariff,
                           long priority,
                           LocalDateTime startDateTime,
                           LocalDateTime endDateTime,
                           double price) {}
