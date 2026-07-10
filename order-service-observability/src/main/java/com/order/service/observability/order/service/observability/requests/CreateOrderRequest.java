package com.order.service.observability.order.service.observability.requests;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateOrderRequest(@NotNull Long customerId, @NotEmpty List<OrderItemRequest> items) {}