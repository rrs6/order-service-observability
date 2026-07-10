package com.order.service.observability.order.service.observability.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.order.service.observability.order.service.observability.entities.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    Optional<OrderEntity> findById(Long id);
}
