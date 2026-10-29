package com.homework4;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;

import com.homework4.model.Cart;
import com.homework4.model.CartItem;
import com.homework4.model.CashPayment;
import com.homework4.model.Food;
import com.homework4.repository.FoodRepository;

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

        while(isRunningApp){
            // * Dependency injection
            
            FoodRepository foodRepository = new FoodRepository();

            List<Food> foods = foodRepository.getAllFoods();

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
                    System.out.println("\n==================================");
                    System.out.println("         MENU MAKANAN         ");
                    System.out.println("==================================");
                    System.out.println();

                    for (Food food : foods) {
                        System.out.println(food.showProduct());
                    }

                    System.out.println();
                    System.out.println("==================================");
                    System.out.println();

                    input.nextLine();
                    System.out.println("Pilih makanan: ");
                    String chooseFoodId = input.nextLine();

                    System.out.println("Masukkan quantity: ");
                    int orderQuantity = input.nextInt();

                    try {
                        Food selectedFood = findFood(foods, chooseFoodId)
                        .orElseThrow(() -> new NoSuchElementException("Makanan tidak ditemukan"));

                        cart.addItem(selectedFood, orderQuantity);
                        System.out.println("\n"+selectedFood.getName()+" Berhasil ditambahkan ke keranjang "+orderQuantity+" porsi !");    
                        
                    } catch (NoSuchElementException e) {
                        System.out.println("Error : " + e.getMessage());
                    }
                    
                    break;
                case 2:
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
                        System.out.println(i+". "+food.getName()+" "+item.getQuantity()+ " porsi x" +" Rp."+food.getPrice()+" = "+"Rp."+item.getSubTotal());
                    }
                    
                    System.out.println();
                    System.out.println("==================================");
                    System.out.println("TOTAL PEMBAYARAN = Rp."+cart.calculateTotal());
                    System.out.println("==================================");
                    System.out.println("Checkout (Y/N): ");
                    String isCheckOut = input.nextLine();
                    input.nextLine();
                    break;
                case 3:
                    int totalPayment = cart.calculateTotal();
                    System.out.println();
                    System.out.println("Metode Pembayaran: ");
                    System.out.println("1. Cash");
                    System.out.println("2. QRIS");
                    
                    input.nextLine();
                    System.out.println("Pilih metode: ");
                    String paymentMethod = input.nextLine();

                    System.out.println("Masukkan uang");
                    int balance = input.nextInt();

                    System.out.println("=============== RECEIPT ===============\n");

                    int j = 0;
                    for(CartItem item : cart.getItems()){
                        Food food = item.getFood();
                        j++;
                        System.out.println(j+". "+food.getName()+" ("+item.getQuantity()+ "x)" +"\t = "+"Rp."+item.getSubTotal());
                    }

                    System.out.println("\n---------------------------------------");
                    System.out.println("Total \t\tRp."+totalPayment);
                    System.out.println("Bayar \t\tRp"+balance);

                    // TODO: biar payment method masuk sbg Cash / Qris
                    if(paymentMethod == "1".trim()){

                        // CashPayment cash = new CashPayment(balance);

                        // cash.Purchase(balance, totalPayment);
                        System.out.println("Pembayaran Cash");

                    }else if(paymentMethod == "2".trim()){
                        System.out.println("Pembayaran QRIS");
                    } else{
                        System.out.println("Metode pembayaran belum tersedia");
                        return;
                    }
                    
                    System.out.println("Terima kasih sudah membeli!\n");
                    isRunningApp=false;
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
