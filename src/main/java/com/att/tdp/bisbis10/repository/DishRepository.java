package com.att.tdp.bisbis10.repository;

import com.att.tdp.bisbis10.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DishRepository extends JpaRepository<Dish, Integer> {
    List<Dish> findByRestaurantId(int restaurantId);
}
