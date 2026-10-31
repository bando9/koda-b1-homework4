package com.homework4;

import java.util.List;
import java.util.Scanner;

import com.homework4.model.Cart;
import com.homework4.model.Food;
import com.homework4.repository.FoodRepository;
import com.homework4.service.OrderService;

public class App 
{
    public static void main( String[] args ){

        boolean isRunningApp = true;

        System.out.println();
        System.out.println("==================================");
        System.out.println("SELAMAT DATANG DI BAKSO SATELIT");
        System.out.println("==================================");
        System.out.println();
        
        // * Declare Cart
        Cart cart = new Cart();
        
        FoodRepository foodRepository = new FoodRepository();
        
        List<Food> foods = foodRepository.getAllFoods();

        while(isRunningApp){
        Scanner input = new Scanner(System.in);
        
        List<String> menu = List.of("1. Lihat Menu Makanan","2. Keranjang","3. Checkout","4. Exit");
        
        System.out.println("\n============ MENU UTAMA ============\n");
        
        for(String m: menu){
            System.out.println(m);
        }

        OrderService orderService = new OrderService(foodRepository, cart,input);
        
        
        System.out.println("Silahkan pilih menu (1-4): ");
        int inputMenu = input.nextInt();
        
            switch (inputMenu) {
                case 1:
                    orderService.showMenuAndOrder();
                    break;
                case 2:
                    orderService.showCart(cart, input);
                    break;
                case 3:
                    input.nextLine();
                    orderService.checkout(cart, input);
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

}
