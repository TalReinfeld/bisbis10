package com.att.tdp.bisbis10.repository;

import com.att.tdp.bisbis10.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}
