package com.order.service.observability.order.service.observability.services.interfaces;

import com.order.service.observability.order.service.observability.entities.OrderEntity;
import com.order.service.observability.order.service.observability.requests.CreateOrderRequest;
import com.order.service.observability.order.service.observability.requests.UpdateOrderRequest;

public interface OrderService {
    OrderEntity createOrder(CreateOrderRequest request);
    OrderEntity findOrderBy(Long orderId);
    void deleteOrderBy(Long orderId);
    void updateOrderBy(UpdateOrderRequest request, Long orderId);
}
