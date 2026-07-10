package com.order.service.observability.order.service.observability.requests;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.order.service.observability.order.service.observability.enums.OrderStatusEnum;

public record OrderResponse (
    Long id,
    Long customerId,
    OrderStatusEnum status,
    BigDecimal totalAmount,
    LocalDateTime createdAt,
    LocalDateTime updatedAt) {
}
