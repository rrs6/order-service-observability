package com.order.service.observability.order.service.observability.utils;

import com.order.service.observability.order.service.observability.entities.OrderEntity;
import com.order.service.observability.order.service.observability.enums.OrderStatusEnum;

public class OrderTransitionValidator {
    public static boolean canTransitionTo(OrderStatusEnum currentStatus, OrderStatusEnum newStatus) {
        return OrderEntity.TRANSITIONS.get(currentStatus).contains(newStatus);
    }
}
