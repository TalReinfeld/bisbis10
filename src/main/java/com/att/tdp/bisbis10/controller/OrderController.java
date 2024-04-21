package com.att.tdp.bisbis10.controller;

import com.att.tdp.bisbis10.dto.OrderDto;
import com.att.tdp.bisbis10.service.OrderService;
import com.att.tdp.bisbis10.validator.Validator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderDto orderDto) {
        try {
            Validator.validateRestaurantId(orderService.checkRestaurantIdExists(orderDto.getRestaurantId()));
            OrderDto savedOrder = orderService.createOrder(orderDto);
            String orderId = savedOrder.getOrderId();
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("orderId", orderId);

            return ResponseEntity.ok(responseBody);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }
}
