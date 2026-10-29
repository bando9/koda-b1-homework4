package com.homework4.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.homework4.model.Food;
import com.homework4.model.Cart;
import com.homework4.repository.FoodRepository;

public class OrderService {
    private final FoodRepository foodRepository;
    private final Cart cart;
    private final Scanner input;

    public OrderService(FoodRepository foodRepository, Cart cart, Scanner input){
        this.foodRepository = foodRepository;
        this.cart = cart;
        this.input = input;
    }

    public void orderAndShowMenu(){
        try {
            System.out.println("\n==================================");
            System.out.println("         MENU MAKANAN         ");
            System.out.println("==================================");
            System.out.println();

            List<Food> foods = foodRepository.getAllFoods();


                        for (Food food : foods) {
                            System.out.println(food.showProduct());
                        }

                        System.out.println();
                        System.out.println("==================================");
                        System.out.println();

                        input.nextLine();
                        System.out.println("Pilih makanan: ");
                        String chooseFoodId = input.nextLine();

                        Food selectedFood = foodRepository.findById(chooseFoodId).orElseThrow(() -> new NoSuchElementException("Makanan tidak ditemukan"));
                        
                        System.out.println("Masukkan quantity: ");
                        int orderQuantity = input.nextInt();


                        cart.addItem(selectedFood, orderQuantity);
                        System.out.println("\n"+selectedFood.getName()+" Berhasil ditambahkan ke keranjang "+orderQuantity+" porsi !");    
                        
                    } catch (NoSuchElementException e) {
                        System.out.println("Error : " + e.getMessage());
                    }
    }
}
