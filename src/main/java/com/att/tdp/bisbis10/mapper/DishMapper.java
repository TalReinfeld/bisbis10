package com.att.tdp.bisbis10.mapper;

import com.att.tdp.bisbis10.dto.DishBodyDto;
import com.att.tdp.bisbis10.dto.DishDto;
import com.att.tdp.bisbis10.entity.Dish;

public class DishMapper {
    public static DishDto mapToDishDto(Dish dish) {
        return new DishDto(
                dish.getId(),
                dish.getName(),
                dish.getDescription(),
                dish.getPrice(),
                dish.getRestaurantId()
        );
    }

    public static Dish mapToDish(DishDto dishDto) {
        return new Dish(
                dishDto.getId(),
                dishDto.getName(),
                dishDto.getDescription(),
                dishDto.getPrice(),
                dishDto.getRestaurantId()
        );
    }

    public static DishBodyDto mapToDishBodyDto(DishDto dishDto) {
        return new DishBodyDto(
                dishDto.getId(),
                dishDto.getName(),
                dishDto.getDescription(),
                dishDto.getPrice()
        );
    }
}
