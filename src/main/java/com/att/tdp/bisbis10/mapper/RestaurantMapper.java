package com.att.tdp.bisbis10.mapper;

import com.att.tdp.bisbis10.utils.Utils;
import com.att.tdp.bisbis10.dto.*;
import com.att.tdp.bisbis10.entity.Restaurant;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RestaurantMapper {

    public static RestaurantDto mapToRestaurantDto(Restaurant restaurant) {
        return new RestaurantDto(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.isKosher(),
                restaurant.getAverageRating(),
                restaurant.getTotalRaters(),
                restaurant.getCuisines().stream().map(cuisine -> Utils.toTitleCase(cuisine)).collect(Collectors.toList())
        );
    }

    public static Restaurant mapToRestaurant(RestaurantDto restaurantDto) {
        return new Restaurant(
                restaurantDto.getId(),
                restaurantDto.getName(),
                restaurantDto.isKosher(),
                Objects.requireNonNullElse(restaurantDto.getAverageRating(), 0.0),
                Objects.requireNonNullElse(restaurantDto.getTotalRaters(), 0),
                restaurantDto.getCuisines().stream().map(cuisine -> Utils.toTitleCase(cuisine)).collect(Collectors.toList())
        );
    }

    public static RestaurantBodyDto mapToRestaurantBodyDto(RestaurantDto restaurantDto) {
        return new RestaurantBodyDto(
                restaurantDto.getId(),
                restaurantDto.getName(),
                restaurantDto.isKosher(),
                Objects.requireNonNullElse(restaurantDto.getAverageRating(), 0.0),
                restaurantDto.getCuisines().stream().map(cuisine -> Utils.toTitleCase(cuisine)).collect(Collectors.toList())
        );
    }

    public static RestaurantBodyWithDishesDTO mapToRestaurantBodyWithDishesDto(RestaurantDto restaurantDto, List<DishDto> dishes) {
        return new RestaurantBodyWithDishesDTO(
                restaurantDto.getId(),
                restaurantDto.getName(),
                restaurantDto.isKosher(),
                Objects.requireNonNullElse(restaurantDto.getAverageRating(), 0.0),
                restaurantDto.getCuisines().stream().map(cuisine -> Utils.toTitleCase(cuisine)).collect(Collectors.toList()),
                dishes.stream().map(dishDto -> DishMapper.mapToDishBodyDto(dishDto)).collect(Collectors.toList())
        );
    }

//    public RestaurantDto mapRequestBodyToRestaurantDto(Map<String, Object> requestBody) {
//        String name = (String) requestBody.get("name");
//        Boolean isKosher = (Boolean) requestBody.get("isKosher");
//        // Map other parameters
//
//        return new RestaurantDto(name, isKosher);
//    }
}
