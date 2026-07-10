package com.order.service.observability.order.service.observability.requests;

import java.math.BigDecimal;

public record OrderItemRequest(Long productId, Integer quantity, BigDecimal unitPrice) {}