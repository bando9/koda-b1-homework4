package com.homework4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args ){
        System.out.println("==================================");
        System.out.println("SELAMAT DATANG DI BAKSO SATELIT");
        System.out.println("==================================");

        System.out.println();

         // * Dependency injection
        List<Food> foods = new ArrayList<>();
        
        Food food1 = new Food(1, "Bakso Urat",13000, 10);
        Food food2 = new Food(2, "Bakso Tetelan", 16000 , 20);
        Food food3 = new Food(3, "Bakso Iga", 18000 , 15);
        Food food4 = new Food(4, "Bakso Rudal", 20000 , 5);
        Food food5 = new Food(5, "Bakso Telor", 20000, 50);

        foods.add(food1);
        foods.add(food2);
        foods.add(food3);
        foods.add(food4);
        foods.add(food5);

        boolean isRunningApp = true;

        Scanner input = new Scanner(System.in);        

        List<String> menu = List.of("1. Lihat Menu Makanan","2. Keranjang","3. Checkout","4. Exit");

        System.out.println("\n============ MENU UTAMA ============\n");
        
        for(String m: menu){
            System.out.println(m);
        }

        System.out.println("Enter menu (1-4): ");
        int inputMenu = input.nextInt();

        switch (inputMenu) {
            case 1:
                // System.out.println("Lihat Menu");    
                // showProduct(foods);
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
                break;
            case 2:
                System.out.println("Keranjang");                
                break;
            case 3:
                System.out.println("Checkout");                
                break;
            case 4:
                System.out.println("Exit");                
                break;
            default:
                System.out.println("Tidak ada pilihan, coba 1-4");
                break;
        }

        
       

        // foods.add(new Food(1, "Bakso Urat",13000, 10));
        // foods.add(new Food(2, "Bakso Tetelan", 16000 , 20));
        // foods.add(new Food(3, "Bakso Iga", 18000 , 15));
        // foods.add(new Food(4, "Bakso Rudal", 20000 , 5));
        // foods.add(new Food(5, "Bakso Telor", 20000, 50));
        
        
       
        
        // * Declare Cart

        Cart cart = new Cart();

        // * menambahkan pesanan
        // System.out.println("menambahkan pesanan");
        // cart.addItem(food1, 2);
        // cart.addItem(food1, 12);
        // System.out.println("pesanan telah ditambahkan!");

        // System.out.println();
        // System.out.println("==================================");
        // System.out.println("             KERANJANG            ");
        // System.out.println("==================================");
        // System.out.println();
        
        // * Show Cart Item
        // int i = 0;
        // for(CartItem item : cart.getItems()){
        //     Food food = item.getFood();
        //     i++;
        //     System.out.println(i+" "+food.getName()+" "+item.getQuantity()+ " porsi x" +" Rp."+food.getPrice()+" = "+"Rp."+item.getSubTotal());
        // }
        
        // System.out.println();
        // System.out.println("==================================");
        // System.out.println("TOTAL PEMBAYARAN = Rp."+cart.calculateTotal());
        // System.out.println("==================================");
        // System.out.println("Checkout (Y/N): ");
        // String isCheckOut = input.nextLine();
        


        // System.out.println();
    }

    // public static void showProduct(Food foods){
    //      // * Show Menu Foods
    //     System.out.println("==================================");
    //     System.out.println("         MENU MAKANAN         ");
    //     System.out.println("==================================");
    //     System.out.println();

    //     for (Food food : foods) {
    //         System.out.println(food.showProduct());
    //     }

        
    //     System.out.println();
    //     System.out.println("==================================");
    //     System.out.println();
    // }

}
