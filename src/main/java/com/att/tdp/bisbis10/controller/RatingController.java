package com.att.tdp.bisbis10.controller;

import com.att.tdp.bisbis10.dto.RateDto;
import com.att.tdp.bisbis10.service.RatingService;
import com.att.tdp.bisbis10.validator.Validator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/ratings")
public class RatingController {
    private RatingService ratingService;

    //Update a restaurant
    @PostMapping
    public ResponseEntity<?> updateRestaurant(@RequestBody RateDto rateDto) {
        try {
            Validator.validateRestaurantId(ratingService.checkIdExists(rateDto.getRestaurantId()));
            Validator.validateRating(rateDto.getRating());
            ratingService.rateRestaurant(rateDto);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
