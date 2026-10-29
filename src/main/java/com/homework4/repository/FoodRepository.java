package com.homework4.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.homework4.model.Food;

public class FoodRepository {
    private final List<Food> foods = new ArrayList<>();

    public FoodRepository(){
        initData();
    }

    private void initData(){
        foods.add(new Food("1", "Bakso Urat",13000, 10));
        foods.add(new Food("2", "Bakso Tetelan", 16000 , 20));
        foods.add(new Food("3", "Bakso Iga", 18000 , 15));
        foods.add(new Food("4", "Bakso Rudal", 20000 , 5));
        foods.add(new Food("5", "Bakso Telor", 20000, 50));    
    }

    public Optional<Food> findById(String id){
        return foods.stream().filter(food -> food.getId().equals(id.trim())).findFirst();
    }

    public List<Food> getAllFoods(){
        return new ArrayList<>(foods);
    }
}
