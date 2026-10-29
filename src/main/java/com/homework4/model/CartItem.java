package com.homework4.model;

public class CartItem {
    private Food food;
    private int quantity;

    public CartItem(Food food, int quantity){
        if(quantity < 0){
            throw new IllegalArgumentException("quantity must be more then 0");
        }
        this.food = food;
        this.quantity=quantity;
    }

    public int getSubTotal(){
        return food.getPrice() * quantity;
    }

    public Food getFood(){
        return food;
    }

    public int getQuantity(){
        return quantity;
    }
}
