package com.att.tdp.bisbis10.validator;

import com.att.tdp.bisbis10.dto.DishDto;
import com.att.tdp.bisbis10.dto.RestaurantDto;
import com.att.tdp.bisbis10.entity.Dish;
import com.att.tdp.bisbis10.entity.OrderItem;
import com.att.tdp.bisbis10.exception.InvalidRequestException;
import com.att.tdp.bisbis10.repository.RestaurantRepository;

import java.util.List;

public class Validator {

    public static void validateRestaurantDto(RestaurantDto restaurantDto) {
        validateString(restaurantDto.getName());
        validateCuisines(restaurantDto.getCuisines());
    }

    public static void validateCuisines(List<String> cuisines) {
        if (cuisines.isEmpty()) {
            throw new InvalidRequestException("cuisines must contain at least one cuisine.");
        }
    }
    public static void validateString(String s) {
        if (s == null || s.isEmpty()) {
            throw new InvalidRequestException("String name cannot be empty.");
        }
    }

    public static void validateRating(double rating) {
        if (rating < 0 || rating > 5) {
            throw new InvalidRequestException("Invalid 'rating' value. It must be between 0 and 5.");
        }
    }

    public static void validateRestaurantId(boolean exists) {
        if (!exists) {
            throw new InvalidRequestException("The restaurant Id does not exists.");
        }
    }

    public static void validateAmount(int amount) {
        if (amount < 0) {
            throw new InvalidRequestException("Invalid 'amount' value. It must be greater or equal to 0.");
        }
    }

    public static void validateOrderItem(int dishId, List<Dish> dishList, int amount) {
        validateDishInRestaurant(dishId, dishList);
        validateAmount(amount);
    }

    public static void validateDishInRestaurant(int dishId, List<Dish> dishList) {
        if (dishList.stream().noneMatch(dish -> dish.getId() == dishId)) {
            throw new InvalidRequestException("The dish Id does not exists in this restaurant.");
        }
    }

    public static void validateDish(DishDto dishDto) {
        validateString(dishDto.getName());
        validateString(dishDto.getDescription());
    }
}
