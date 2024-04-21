package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.dto.RateDto;
import com.att.tdp.bisbis10.dto.RestaurantDto;

public interface RatingService {
    RestaurantDto rateRestaurant(RateDto rateDto);

    boolean checkIdExists(int id);
}
