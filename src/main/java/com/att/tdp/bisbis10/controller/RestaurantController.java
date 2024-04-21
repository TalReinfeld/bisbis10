package com.att.tdp.bisbis10.controller;

import com.att.tdp.bisbis10.dto.RestaurantBodyDto;
import com.att.tdp.bisbis10.dto.RestaurantBodyWithDishesDTO;
import com.att.tdp.bisbis10.dto.RestaurantDto;
import com.att.tdp.bisbis10.mapper.RestaurantMapper;
import com.att.tdp.bisbis10.service.DishService;
import com.att.tdp.bisbis10.service.RestaurantService;
import com.att.tdp.bisbis10.validator.Validator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.att.tdp.bisbis10.utils.Utils;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private RestaurantService restaurantService;
    private DishService dishService;

    //Add a restaurant:
    @PostMapping
    public ResponseEntity<?> createRestaurant(@RequestBody RestaurantDto restaurantDto) {
        try {
            Validator.validateRestaurantDto(restaurantDto);
            System.out.println("------------------------------name = " + restaurantDto.getName());
            System.out.println("------------------------------isKosher = " + restaurantDto.isKosher());
            System.out.println("------------------------------cuisines = " + restaurantDto.getCuisines());
            restaurantService.createRestaurant(restaurantDto);
            return ResponseEntity.created(null).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    //Get restaurant
    @GetMapping("{id}")
    public ResponseEntity<RestaurantBodyWithDishesDTO> getRestaurantById(@PathVariable("id") int restaurantId) {
        RestaurantBodyWithDishesDTO restaurantBodyDto = RestaurantMapper
                .mapToRestaurantBodyWithDishesDto(restaurantService.getRestaurantById(restaurantId),
                        dishService.getAllDishesByRestaurant(restaurantId));
        return ResponseEntity.ok(restaurantBodyDto);
    }

    //Get all restaurants
    @GetMapping
    public ResponseEntity<List<RestaurantBodyDto>> getAllRestaurants() {
        List<RestaurantBodyDto> restaurants = restaurantService.getAllRestaurants()
                .stream()
                .map(restaurantDto -> RestaurantMapper.mapToRestaurantBodyDto(restaurantDto))
                .collect(Collectors.toList());
        return ResponseEntity.ok(restaurants);
    }

    //Update a restaurant
    @PutMapping("{id}")
    public ResponseEntity<?> updateRestaurant(@PathVariable("id") int restaurantId,
                                                          @RequestBody RestaurantDto updatedRestaurant) {
        try {
            Validator.validateCuisines(updatedRestaurant.getCuisines());
            restaurantService.updateRestaurant(restaurantId, updatedRestaurant);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    //Delete a restaurant
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteRestaurant(@PathVariable("id") int restaurantId) {
        try {
            restaurantService.deleteRestaurant(restaurantId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    //Get restaurants by cuisine
    @GetMapping(params = "cuisine")
    public ResponseEntity<List<RestaurantBodyDto>> getRestaurantsByCuisine(@RequestParam("cuisine") String cuisine) {
        List<RestaurantBodyDto> restaurants = restaurantService.getRestaurantsByCuisine(Utils.toTitleCase(cuisine))
                .stream()
                .map(restaurantDto -> RestaurantMapper.mapToRestaurantBodyDto(restaurantDto))
                .collect(Collectors.toList());
        return ResponseEntity.ok(restaurants);
    }

}
