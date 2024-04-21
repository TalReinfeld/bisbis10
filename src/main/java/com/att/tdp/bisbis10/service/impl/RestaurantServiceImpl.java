package com.att.tdp.bisbis10.service.impl;

import com.att.tdp.bisbis10.utils.Utils;
import com.att.tdp.bisbis10.dto.RestaurantDto;
import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.exception.ResourceNotFoundException;
import com.att.tdp.bisbis10.mapper.RestaurantMapper;
import com.att.tdp.bisbis10.repository.RestaurantRepository;
import com.att.tdp.bisbis10.service.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private RestaurantRepository restaurantRepository;

    @Override
    public RestaurantDto createRestaurant(RestaurantDto restaurantDto) {

        Restaurant restaurant = RestaurantMapper.mapToRestaurant(restaurantDto);
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);
        return RestaurantMapper.mapToRestaurantDto(savedRestaurant);
    }

    @Override
    public RestaurantDto getRestaurantById(int restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Restaurant with the id " + restaurantId + " does not exists"));
        return RestaurantMapper.mapToRestaurantDto(restaurant);
    }

    @Override
    public List<RestaurantDto> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants.stream().map((restaurant) -> RestaurantMapper.mapToRestaurantDto(restaurant))
                .collect(Collectors.toList());
    }

    @Override
    public RestaurantDto updateRestaurant(int restaurantId, RestaurantDto updatedRestaurant) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(
                () -> new ResourceNotFoundException("Restaurant with the id " + restaurantId + " does not exists")
        );

        restaurant.setCuisines(updatedRestaurant.getCuisines()
                .stream()
                .map(cuisine -> Utils.toTitleCase(cuisine))
                .collect(Collectors.toList()));
        Restaurant updatedRestaurantObj = restaurantRepository.save(restaurant);

        return RestaurantMapper.mapToRestaurantDto(updatedRestaurantObj);
    }

    @Override
    public void deleteRestaurant(int restaurantId) {

        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(
                () -> new ResourceNotFoundException("Restaurant with the id " + restaurantId + " does not exists")
        );

        restaurantRepository.deleteById(restaurantId);
    }



    @Override
    public List<RestaurantDto> getRestaurantsByCuisine(String cuisine) {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants.stream()
                .filter(restaurant -> restaurant.getCuisines().contains(cuisine))
                .map((restaurant) -> RestaurantMapper.mapToRestaurantDto(restaurant))
                .collect(Collectors.toList());
    }

}
