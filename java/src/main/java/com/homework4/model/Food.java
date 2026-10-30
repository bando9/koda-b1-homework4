package com.homework4.model;

public class Food {
    private String id;
    private String name;
    private int price;
    private int stock;

    public String getId() {
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

    public Food(String id, String name, int price, int stock){
        if(id == null || id.isBlank()){
            throw new IllegalArgumentException("id must be filled in");
        }
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("name must be filled in");
        }
        if(price < 0){
            throw new IllegalArgumentException("price must be more then 0");
        }
        if(stock < 0){
            throw new IllegalArgumentException("stock must be more then 0");
        }
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String showProduct(){
        return (this.id+". "+ this.name+" - " + "Rp."+this.price+" (stock: "+this.stock+")");
    }

    
}
