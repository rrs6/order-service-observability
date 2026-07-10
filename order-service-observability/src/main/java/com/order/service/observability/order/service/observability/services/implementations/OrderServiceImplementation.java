package com.order.service.observability.order.service.observability.services.implementations;

import org.springframework.stereotype.Service;

import com.order.service.observability.order.service.observability.entities.OrderEntity;
import com.order.service.observability.order.service.observability.exceptions.OrderNotFoundException;
import com.order.service.observability.order.service.observability.mappers.OrderMapper;
import com.order.service.observability.order.service.observability.repositories.OrderRepository;
import com.order.service.observability.order.service.observability.requests.CreateOrderRequest;
import com.order.service.observability.order.service.observability.requests.UpdateOrderRequest;
import com.order.service.observability.order.service.observability.services.interfaces.OrderService;

@Service
public class OrderServiceImplementation implements OrderService {

    private final OrderRepository orderRepository;
    public OrderServiceImplementation(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderEntity createOrder(CreateOrderRequest request) {
        OrderEntity createdOrder = OrderMapper.toEntity(request);
        return this.orderRepository.save(createdOrder);
    }

    @Override
    public OrderEntity findOrderBy(Long orderId) {
        OrderEntity order = orderRepository.findById(orderId)
        .orElseThrow(() -> new OrderNotFoundException("Order not found"));
        return order;
    }

    @Override
    public void deleteOrderBy(Long orderId) {
        OrderEntity order = orderRepository.findById(orderId)
        .orElseThrow(() -> new OrderNotFoundException("Order not found"));
        this.orderRepository.delete(order);
    }

    @Override
    public void updateOrderBy(UpdateOrderRequest request, Long orderId) {
        OrderEntity order = orderRepository.findById(orderId)
        .orElseThrow(() -> new OrderNotFoundException("Order not found"));
        order.transitionOrderStatusTo(request.status());
        orderRepository.save(order);
    }
    
}
