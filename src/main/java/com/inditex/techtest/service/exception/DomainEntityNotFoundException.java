package com.inditex.techtest.service.exception;

public class DomainEntityNotFoundException extends RuntimeException {
    public DomainEntityNotFoundException(String msg) {
        super(msg);
    }
}
