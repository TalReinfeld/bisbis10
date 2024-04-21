package com.att.tdp.bisbis10.service.impl;

import com.att.tdp.bisbis10.dto.OrderDto;
import com.att.tdp.bisbis10.entity.Dish;
import com.att.tdp.bisbis10.entity.Order;
import com.att.tdp.bisbis10.mapper.OrderMapper;
import com.att.tdp.bisbis10.repository.DishRepository;
import com.att.tdp.bisbis10.repository.OrderRepository;
import com.att.tdp.bisbis10.repository.RestaurantRepository;
import com.att.tdp.bisbis10.service.OrderService;
import com.att.tdp.bisbis10.validator.Validator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private RestaurantRepository restaurantRepository;
    private DishRepository dishRepository;

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        Order order = OrderMapper.mapToOrder(orderDto);
        List<Dish> dishes = dishRepository.findByRestaurantId(order.getRestaurantId());
        order.getOrderItems().forEach(orderItem -> Validator.validateOrderItem(orderItem.getDishId(), dishes, orderItem.getAmount()));

        Order savedOrder = orderRepository.save(order);
        return OrderMapper.mapToOrderDto(savedOrder);
    }

    public boolean checkRestaurantIdExists(int id) {
        return restaurantRepository.existsById(id);
    }

}
