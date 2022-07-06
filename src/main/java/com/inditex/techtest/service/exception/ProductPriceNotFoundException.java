package com.inditex.techtest.service.exception;

public class ProductPriceNotFoundException extends DomainEntityNotFoundException {
    public ProductPriceNotFoundException(String msg) {
        super(msg);
    }
}
