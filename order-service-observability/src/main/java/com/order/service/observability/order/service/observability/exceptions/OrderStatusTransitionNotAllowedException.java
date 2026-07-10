package com.order.service.observability.order.service.observability.exceptions;

public class OrderStatusTransitionNotAllowedException extends RuntimeException {

    public OrderStatusTransitionNotAllowedException(String message) {
        super(message);
    }
}