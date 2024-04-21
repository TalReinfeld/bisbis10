package com.att.tdp.bisbis10.controller;

import com.att.tdp.bisbis10.dto.DishBodyDto;
import com.att.tdp.bisbis10.dto.DishDto;
import com.att.tdp.bisbis10.dto.RestaurantDto;
import com.att.tdp.bisbis10.mapper.DishMapper;
import com.att.tdp.bisbis10.service.DishService;
import com.att.tdp.bisbis10.validator.Validator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/restaurants")
public class DishController {
    private DishService dishService;

    // Add a dish
    @PostMapping("{id}/dishes")
    public ResponseEntity<?> createDish(@RequestBody DishDto dishDto, @PathVariable("id") int restaurantId) {
        try {
            dishDto.setRestaurantId(restaurantId);
            dishService.createDish(dishDto);
            return ResponseEntity.created(null).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Update a dish
    @PutMapping("{id}/dishes/{dishId}")
    public ResponseEntity<?> updateDish(@PathVariable("id") int restaurantId,
                                                          @RequestBody DishDto updatedDish,
                                              @PathVariable("dishId") int dishId) {
        try {
            dishService.updateDish(restaurantId, updatedDish, dishId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    //Delete a dish
    @DeleteMapping("{id}/dishes/{dishId}")
    public ResponseEntity<?> deleteRestaurant(@PathVariable("dishId") int dishId) {
        try {
            dishService.deleteDish(dishId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    //Get dishes by restaurant
    @GetMapping("{id}/dishes")
    public ResponseEntity<List<DishBodyDto>> getDishesByRestaurant(@PathVariable("id") int restaurantId) {
        List<DishBodyDto> dishes = dishService.getAllDishesByRestaurant(restaurantId)
                .stream()
                .map(dishDto -> DishMapper.mapToDishBodyDto(dishDto))
                .collect(Collectors.toList());
        return ResponseEntity.ok(dishes);
    }
}
