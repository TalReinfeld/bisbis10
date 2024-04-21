package com.att.tdp.bisbis10.service.impl;

import com.att.tdp.bisbis10.dto.DishDto;
import com.att.tdp.bisbis10.dto.RestaurantDto;
import com.att.tdp.bisbis10.entity.Dish;
import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.exception.ResourceNotFoundException;
import com.att.tdp.bisbis10.mapper.DishMapper;
import com.att.tdp.bisbis10.mapper.RestaurantMapper;
import com.att.tdp.bisbis10.repository.DishRepository;
import com.att.tdp.bisbis10.repository.RestaurantRepository;
import com.att.tdp.bisbis10.service.DishService;
import com.att.tdp.bisbis10.validator.Validator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DishServiceImpl implements DishService {

    private DishRepository dishRepository;
    private RestaurantRepository restaurantRepository;

    @Override
    public DishDto createDish(DishDto dishDto) {
        Validator.validateRestaurantId(restaurantRepository.existsById(dishDto.getRestaurantId()));
        Validator.validateDish(dishDto);
        Dish dish = DishMapper.mapToDish(dishDto);
        Dish savedDish = dishRepository.save(dish);
        return DishMapper.mapToDishDto(savedDish);
    }

    @Override
    public DishDto updateDish(int restaurantId, DishDto updatedDish, int dishId) {
        Dish dish = dishRepository.findById(dishId).orElseThrow(
                () -> new ResourceNotFoundException("Dish with the id " + dishId + " does not exists")
        );

        Validator.validateString(updatedDish.getDescription());

        dish.setDescription(updatedDish.getDescription());
        dish.setPrice(updatedDish.getPrice());

        Dish updatedDishObj = dishRepository.save(dish);

        return DishMapper.mapToDishDto(updatedDishObj);
    }

    @Override
    public void deleteDish(int dishId) {
        Dish dish = dishRepository.findById(dishId).orElseThrow(
                () -> new ResourceNotFoundException("Dish with the id " + dishId + " does not exists")
        );

        dishRepository.deleteById(dishId);
    }

    @Override
    public List<DishDto> getAllDishesByRestaurant(int restaurantId) {
        List<Dish> dishes = dishRepository.findAll();
        return dishes.stream().filter(dish -> dish.getRestaurantId() == restaurantId)
                .map((dish) -> DishMapper.mapToDishDto(dish))
                .collect(Collectors.toList());
    }
}

