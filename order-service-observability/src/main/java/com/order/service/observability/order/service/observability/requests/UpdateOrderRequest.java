package com.order.service.observability.order.service.observability.requests;

import com.order.service.observability.order.service.observability.enums.OrderStatusEnum;

import jakarta.validation.constraints.NotNull;

public record UpdateOrderRequest(
        @NotNull OrderStatusEnum status
) {
}
