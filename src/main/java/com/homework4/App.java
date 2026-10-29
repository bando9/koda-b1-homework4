package com.homework4;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args ){

        boolean isRunningApp = true;

        System.out.println();
        System.out.println("==================================");
        System.out.println("SELAMAT DATANG DI BAKSO SATELIT");
        System.out.println("==================================");
        System.out.println();
        
        Cart cart = new Cart();


        while(isRunningApp){

            // * Dependency injection
            List<Food> foods = new ArrayList<>();
            
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

            Scanner input = new Scanner(System.in);        

            List<String> menu = List.of("1. Lihat Menu Makanan","2. Keranjang","3. Checkout","4. Exit");

            System.out.println("\n============ MENU UTAMA ============\n");
            
            for(String m: menu){
                System.out.println(m);
            }


            System.out.println("Silahkan pilih menu (1-4): ");
            int inputMenu = input.nextInt();

            switch (inputMenu) {
                case 1:
                    System.out.println("==================================");
                    System.out.println("         MENU MAKANAN         ");
                    System.out.println("==================================");
                    System.out.println();

                    for (Food food : foods) {
                        System.out.println(food.showProduct());
                    }

                    System.out.println();
                    System.out.println("==================================");
                    System.out.println();

                    // System.out.println("Pilih makanan: ");
                    // String chooseFoodId = input.nextLine();

                    // System.out.println("Masukkan quantity: ");
                    // int inputQuantity = input.nextInt();

                    // TODO: mencari food sesuai i.

                    try {
                        Food selectedFood = findFood(foods, "1")
                        .orElseThrow(() -> new NoSuchElementException("Makanan tidak ditemukan"));

                        cart.addItem(selectedFood, 2);
                        System.out.println(selectedFood.getName()+" Berhasil ditambahkan ke keranjang!");    
                        
                        int k = 0;
                        for(CartItem item : cart.getItems()){
                            Food food = item.getFood();
                            k++;
                            System.out.println(k+". "+food.getName()+" ("+item.getQuantity()+ "x)" +"\t = "+"Rp."+item.getSubTotal());
                        }


                    } catch (NoSuchElementException e) {
                        System.out.println("Error : " + e.getMessage());
                    }

                    
                    break;
                case 2:
                    System.out.println();

                    // * Declare Cart


                    System.out.println();
                    System.out.println("==================================");
                    System.out.println("             KERANJANG            ");
                    System.out.println("==================================");
                    System.out.println();
                    
                    // * Show Cart Item
                    int i = 0;
                    for(CartItem item : cart.getItems()){
                        Food food = item.getFood();
                        i++;
                        System.out.println(i+" "+food.getName()+" "+item.getQuantity()+ " porsi x" +" Rp."+food.getPrice()+" = "+"Rp."+item.getSubTotal());
                    }
                    
                    System.out.println();
                    System.out.println("==================================");
                    System.out.println("TOTAL PEMBAYARAN = Rp."+cart.calculateTotal());
                    System.out.println("==================================");
                    System.out.println("Checkout (Y/N): ");
                    String isCheckOut = input.nextLine();               
                    break;
                case 3:
                    System.out.println();
                    System.out.println("Checkout");

                    System.out.println("=============== RECEIPT ===============");

                    int j = 0;
                    for(CartItem item : cart.getItems()){
                        Food food = item.getFood();
                        j++;
                        System.out.println(j+". "+food.getName()+" ("+item.getQuantity()+ "x)" +"\t = "+"Rp."+item.getSubTotal());
                    }

                    System.out.println("---------------------------------------");
                    System.out.println("Total              \t\t\t\tRp."+cart.calculateTotal());
                    System.out.println("Bayar  Rp50.000"+"\nKembalian  Rp5.000"+"\nTerima kasih sudah membeli!");
                    break;
                case 4:
                    System.out.println("Exit");
                    isRunningApp = false;
                    break;
                default:
                    System.out.println("Tidak ada pilihan, coba 1-4");
                    break;
            }
        }        
        
    }

    static Optional<Food> findFood(
    List<Food> foods, String foodID) {
    return foods.stream().filter(food ->
    food.getId().equals(foodID)).findFirst();
    }

}
