package com.att.tdp.bisbis10.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.Pair;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    private String orderId;

    @Column(name = "restaurant_id")
    private int restaurantId;

    @OneToMany(mappedBy = "orderId", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    @PrePersist
    private void ensureId(){
        this.setOrderId(UUID.randomUUID().toString());
    }
}
