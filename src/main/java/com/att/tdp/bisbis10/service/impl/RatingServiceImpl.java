package com.att.tdp.bisbis10.service.impl;

import com.att.tdp.bisbis10.dto.RateDto;
import com.att.tdp.bisbis10.dto.RestaurantDto;
import com.att.tdp.bisbis10.entity.Restaurant;
import com.att.tdp.bisbis10.exception.ResourceNotFoundException;
import com.att.tdp.bisbis10.mapper.RestaurantMapper;
import com.att.tdp.bisbis10.repository.RestaurantRepository;
import com.att.tdp.bisbis10.service.RatingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Service
@AllArgsConstructor
public class RatingServiceImpl implements RatingService {

    private RestaurantRepository restaurantRepository;

    @Override
    public RestaurantDto rateRestaurant(RateDto rateDto) {
        Restaurant restaurant = restaurantRepository.findById(rateDto.getRestaurantId()).orElseThrow(
                () -> new ResourceNotFoundException("Restaurant with the id " + rateDto.getRestaurantId() + " does not exists")
        );

        int newTotalRaters = restaurant.getTotalRaters() + 1;
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        double newAverageRating = Double.parseDouble(decimalFormat
                .format((restaurant.getAverageRating() * restaurant.getTotalRaters() + rateDto.getRating()) / newTotalRaters));

        restaurant.setTotalRaters(newTotalRaters);
        restaurant.setAverageRating(newAverageRating);

        Restaurant updatedRestaurantObj = restaurantRepository.save(restaurant);

        return RestaurantMapper.mapToRestaurantDto(updatedRestaurantObj);
    }

    public boolean checkIdExists(int id) {
        return restaurantRepository.existsById(id);
    }
}
