package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.dto.RestaurantDto;

import java.util.List;

public interface RestaurantService {
    RestaurantDto createRestaurant(RestaurantDto restaurantDto);

    RestaurantDto getRestaurantById(int restaurantId);

    List<RestaurantDto> getAllRestaurants();

    RestaurantDto updateRestaurant(int restaurantId, RestaurantDto updatedRestaurant);

    void deleteRestaurant(int restaurantId);

    List<RestaurantDto> getRestaurantsByCuisine(String cuisine);
}
