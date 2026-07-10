package com.order.service.observability.order.service.observability.mappers;

import java.math.BigDecimal;
import java.util.List;

import com.order.service.observability.order.service.observability.entities.OrderEntity;
import com.order.service.observability.order.service.observability.entities.OrderItemEntity;
import com.order.service.observability.order.service.observability.enums.OrderStatusEnum;
import com.order.service.observability.order.service.observability.requests.CreateOrderRequest;
import com.order.service.observability.order.service.observability.requests.OrderResponse;

public class OrderMapper {
    
    public static OrderEntity toEntity(CreateOrderRequest request) {
        BigDecimal totalAmount = request.items()
        .stream()
        .map(item -> item.unitPrice().multiply(BigDecimal.valueOf(item.quantity())))
        .reduce(BigDecimal.ZERO, BigDecimal::add);

        OrderEntity order = new OrderEntity(request.customerId(), OrderStatusEnum.CREATED, totalAmount);

        List<OrderItemEntity> items = request.items().stream()
        .map(item -> new OrderItemEntity(item.productId(),item.quantity(),item.unitPrice(), order))
        .toList();

        order.setItems(items);
        
        return order;
    }

    public static OrderResponse toResponse(OrderEntity orderEntity) {
        OrderResponse orderResponse = new OrderResponse(orderEntity.getId(),
        orderEntity.getCustomerId(),
        orderEntity.getStatus(),
        orderEntity.getTotalAmount(),
        orderEntity.getCreatedAt(),
        orderEntity.getUpdatedAt());

        return orderResponse;
    }
}
