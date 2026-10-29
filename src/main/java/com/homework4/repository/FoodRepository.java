package com.homework4.repository;

import java.util.ArrayList;
import java.util.List;

import com.homework4.model.Food;

public class FoodRepository {
    private final List<Food> foods = new ArrayList<>();

    public FoodRepository(){
        initData();
    }

    private void initData(){
        Food food1 = new Food("1", "Bakso Urat",13000, 10);
        Food food2 = new Food("2", "Bakso Tetelan", 16000 , 20);
        Food food3 = new Food("3", "Bakso Iga", 18000 , 15);
        Food food4 = new Food("4", "Bakso Rudal", 20000 , 5);
        Food food5 = new Food("5", "Bakso Telor", 20000, 50);

        foods.add(food1);
        foods.add(food2);
        foods.add(food3);
        foods.add(food4);
        foods.add(food5);    
    }

    public List<Food> getAllFoods(){
        return new ArrayList<>(foods);
    }
}
