package com.homework4.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Cart {
    private List<CartItem> items;
    
    public Cart(){
        this.items = new ArrayList<>();
    }

    public boolean isEmpty(){
        return items.isEmpty();
    }

    public int calculateTotal(){
        int total = 0;
        for(CartItem item:items){
            total+=item.getSubTotal();
        }
        return total;
    }

    public void addItem(Food food, int quantity){
        if(quantity <= 0 ){
            System.out.println("Jumlah tidak bisa kurang dari 0");
            return;
        }
        items.add(new CartItem(food, quantity));
    }
    
    public void removeItem(int index){
        if(index<0 || index>items.size()){
            throw new IllegalArgumentException("Id makanan tidak ditemukan");
        }
        items.remove(index);
    }

    public void clearCart(){
        items.clear();
    }

    public List<CartItem> getItems(){
        return items;
    }
    public int sizeItems(){
        return items.size();
    }

    public boolean updateQuantity(String foodId, int newQuantity){
        Optional<CartItem> optionalItem = items.stream().filter(item -> item.getFood().getId().equalsIgnoreCase(foodId.trim())).findFirst();

        if(optionalItem.isEmpty()){
            System.out.println("Item dg ID '"+foodId+"' tidak ditemukan");
            return false;
        }

        CartItem item = optionalItem.get();

        if(newQuantity <= 0){
            items.remove(item);
            System.out.println("quantity dibawah 0 tidak bisa dimasukkan. Item dihapus dari keranjang.");
            return true;
        }

        if(newQuantity > item.getFood().getStock()){
            System.out.println("Maaf stock terbatas, stock tersisa "+item.getFood().getStock());
            return false;
        }

        item.setQuantity(newQuantity);
        System.out.printf("%s porsi terbaru berjumlah %d porsi", item.getFood().getName(), newQuantity);
        return true;
    }
}
