package com.order.service.observability.order.service.observability.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.service.observability.order.service.observability.mappers.OrderMapper;
import com.order.service.observability.order.service.observability.requests.CreateOrderRequest;
import com.order.service.observability.order.service.observability.requests.OrderResponse;
import com.order.service.observability.order.service.observability.requests.UpdateOrderRequest;
import com.order.service.observability.order.service.observability.services.interfaces.OrderService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RequestMapping("/order")
@RestController
public class OrderController {
    private final OrderService orderService;
    
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    //This endpoint finds an order whose orderId matches the value provided in the path.
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrderBy(@PathVariable("orderId") Long orderId) {
        OrderResponse response = OrderMapper.toResponse(this.orderService.findOrderBy(orderId));
        return ResponseEntity.ok(response);
    }

    //This endpoint delete an order whose orderId matches the value provided in the path.
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrderBy(@PathVariable("orderId") Long orderId) {
        this.orderService.deleteOrderBy(orderId);
        return ResponseEntity.ok().build();
    }

    //This endpoint update an order whose orderId matches the value provided in the path.
    @PatchMapping("/{orderId}")
    public ResponseEntity<Void> updateOrderBy(@PathVariable("orderId") Long orderId, @Valid @RequestBody UpdateOrderRequest request) {
        this.orderService.updateOrderBy(request, orderId);
        return ResponseEntity.ok().build();
    }

    //This endpoint returns a paginated list of orders.
    @GetMapping
    public String getOrders() {
        return new String("Pega carai");
    }
    
    //This endpoint serves to create a new order
    @PostMapping()
    public ResponseEntity<Void> createOrder(@Valid @RequestBody CreateOrderRequest request) {
        this.orderService.createOrder(request);
        return ResponseEntity.status(201).build();
    }

}