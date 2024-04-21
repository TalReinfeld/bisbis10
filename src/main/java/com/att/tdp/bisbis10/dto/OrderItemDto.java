package com.att.tdp.bisbis10.dto;

import com.att.tdp.bisbis10.entity.Dish;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {
    private int id;
    private int dishId;
    private int amount;
    private String orderId;
}
