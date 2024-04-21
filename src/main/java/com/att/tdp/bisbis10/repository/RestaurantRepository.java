package com.att.tdp.bisbis10.repository;

import com.att.tdp.bisbis10.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    boolean existsById(int id);

}
