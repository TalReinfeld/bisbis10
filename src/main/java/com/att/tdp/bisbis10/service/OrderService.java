package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.dto.OrderDto;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDto);

    boolean checkRestaurantIdExists(int id);


}
