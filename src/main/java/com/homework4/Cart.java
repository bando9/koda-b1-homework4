package com.homework4;

import java.util.ArrayList;
import java.util.List;

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

    public List<CartItem> getItems(){
        return items;
    }
}
