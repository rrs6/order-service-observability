package com.order.service.observability.order.service.observability.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.order.service.observability.order.service.observability.enums.OrderStatusEnum;
import com.order.service.observability.order.service.observability.exceptions.OrderStatusTransitionNotAllowedException;
import com.order.service.observability.order.service.observability.utils.OrderTransitionValidator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class OrderEntity {

    public static final Map<OrderStatusEnum, List<OrderStatusEnum>> TRANSITIONS = Map.of(
        OrderStatusEnum.CREATED, List.of(
            OrderStatusEnum.PAID,
            OrderStatusEnum.CANCELLED
        ),
        OrderStatusEnum.PAID, List.of(
            OrderStatusEnum.SHIPPED,
            OrderStatusEnum.CANCELLED
        ),
        OrderStatusEnum.SHIPPED, List.of(),
        OrderStatusEnum.CANCELLED, List.of()
    );
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_seq")
    @SequenceGenerator(name = "orders_seq", sequenceName = "orders_seq", allocationSize = 50)
    private Long id;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatusEnum status;

    @Column(name = "total_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemEntity> items = new ArrayList<>();


    public OrderEntity() {}

    public OrderEntity(
            Long customerId,
            OrderStatusEnum status,
            BigDecimal totalAmount
    ) {
        this.customerId = customerId;
        this.status = status;
        this.totalAmount = totalAmount;
    }

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setItems(List<OrderItemEntity> items) {
        this.items = items;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void transitionOrderStatusTo(OrderStatusEnum status) {
        if (!OrderTransitionValidator.canTransitionTo(this.status, status)) {
            throw new OrderStatusTransitionNotAllowedException(
                "Cannot transition order from " + this.status + " to " + status);
        }
        
        this.status = status;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Long getCustomerId() {
        return customerId;
    }
    
    public Long getId() {
        return id;
    }

    public List<OrderItemEntity> getItems() {
        return items;
    }

    public OrderStatusEnum getStatus() {
        return status;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

}
