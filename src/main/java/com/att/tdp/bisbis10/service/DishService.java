package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.dto.DishDto;
import com.att.tdp.bisbis10.dto.RestaurantDto;

import java.util.List;

public interface DishService {
    DishDto createDish(DishDto dishDto);

    DishDto updateDish(int restaurantId, DishDto updatedDish, int dishId);

    void deleteDish(int dishId);

    List<DishDto> getAllDishesByRestaurant(int restaurantId);

}
