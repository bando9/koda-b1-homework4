package com.homework4;

public class Food {
    private int id;
    private String name;
    private int price;
    private int stock;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
    public int getStock() {
        return stock;
    }

    public Food(int id, String name, int price, int stock){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String showProduct(){
        return (this.id+". "+ this.name+" - " + "Rp."+this.price+" (stock: "+this.stock+")");
    }

    
}
