package com.homework4.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Cart {
    private List<CartItem> items;
    
    public Cart(){
        this.items = new ArrayList<>();
    }

    public int calculateTotal(){
        int total = 0;
        for(CartItem item:items){
            total+=item.getSubTotal();
        }
        return total;
    }

    public void addItem(Food food, int quantity){
        items.add(new CartItem(food, quantity));
    }

    // public void reduceItemQuantity(Food food, int quantity){
    //     items.set
    // }

    // public void removeItem(CartItem food){
    //     items.remove(findItemByFood(food));    
    // }

    // private Optional<Food> findItemByFood(Food food){
    //     return items.stream().filter(item -> item.getFood().getId().equals(food.getId())).findFirst();
    // }

    public void clearCart(){
        items.clear();
    }

    public List<CartItem> getItems(){
        return items;
    }
}
