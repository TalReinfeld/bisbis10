package com.att.tdp.bisbis10.mapper;

import com.att.tdp.bisbis10.dto.OrderItemDto;
import com.att.tdp.bisbis10.entity.OrderItem;

public class OrderItemMapper {
    public static OrderItemDto mapToOrderItemDto(OrderItem orderItem) {
        return new OrderItemDto(
                orderItem.getId(),
                orderItem.getDishId(),
                orderItem.getAmount(),
                orderItem.getOrderId()
        );
    }

    public static OrderItem mapToOrderItem(OrderItemDto orderItemDto) {
        return new OrderItem(
                orderItemDto.getId(),
                orderItemDto.getDishId(),
                orderItemDto.getAmount(),
                orderItemDto.getOrderId()
        );
    }
}
