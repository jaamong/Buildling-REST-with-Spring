package com.example.accessingdatajpa.payroll.domain.exception;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(Long id) {
        super();
    }
}
