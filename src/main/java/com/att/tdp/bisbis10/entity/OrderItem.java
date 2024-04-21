package com.att.tdp.bisbis10.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "dish_id")
    private int dishId;

    @JoinColumn(name = "amount")
    private int amount;

    @JoinColumn(name = "order_id")
    private String orderId;
}
