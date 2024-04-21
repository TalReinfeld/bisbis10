package com.att.tdp.bisbis10.entity;

import jakarta.persistence.*;

import java.sql.Array;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "restaurants")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "is_kosher")
    private boolean isKosher;

    @Column(name = "average_rating")
    private double averageRating;

    @Column(name = "total_raters")
    private int totalRaters;

    @ElementCollection
    @CollectionTable(name = "restaurant_cuisines", joinColumns = @JoinColumn(name = "restaurant_id"))
    @Column(name = "cuisine")
    private List<String> cuisines;

//    @OneToMany(mappedBy = "restaurantId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Dish> dishes;


}
