package com.att.tdp.bisbis10.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDto {
    private int id;
    private String name;
    @JsonProperty("isKosher")
    private boolean isKosher;
    private double averageRating;
    private int totalRaters;
    private List<String> cuisines;
}
