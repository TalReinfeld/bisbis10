package com.att.tdp.bisbis10.mapper;

import com.att.tdp.bisbis10.dto.OrderDto;
import com.att.tdp.bisbis10.entity.Order;
import java.util.stream.Collectors;

public class OrderMapper {
    public static OrderDto mapToOrderDto(Order order) {
        return new OrderDto(
                order.getOrderId(),
                order.getRestaurantId(),
                order.getOrderItems().stream()
                        .map(orderItem -> OrderItemMapper.mapToOrderItemDto(orderItem))
                        .collect(Collectors.toList())
        );
    }

    public static Order mapToOrder(OrderDto orderDto) {
        return new Order(
                orderDto.getOrderId(),
                orderDto.getRestaurantId(),
                orderDto.getOrderItems().stream()
                        .map(orderItemDto -> OrderItemMapper.mapToOrderItem(orderItemDto))
                        .collect(Collectors.toList())
        );
    }
}
