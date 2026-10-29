package com.homework4.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.homework4.model.Food;
import com.homework4.model.QrisPayment;
import com.homework4.model.Cart;
import com.homework4.model.CartItem;
import com.homework4.model.CashPayment;
import com.homework4.repository.FoodRepository;

public class OrderService {
    private final FoodRepository foodRepository;
    private final Cart cart;
    private final Scanner input;

    public OrderService(FoodRepository foodRepository, Cart cart, Scanner input){
        this.foodRepository = foodRepository;
        this.cart = cart;
        this.input = input;
    }

    public void showMenuAndOrder(){
        try {
            System.out.println("\n==================================");
            System.out.println("         MENU MAKANAN         ");
            System.out.println("==================================");
            System.out.println();

            List<Food> foods = foodRepository.getAllFoods();


                        for (Food food : foods) {
                            System.out.println(food.showProduct());
                        }

                        System.out.println();
                        System.out.println("==================================");
                        System.out.println();

                        input.nextLine();
                        System.out.println("Pilih makanan: ");
                        String chooseFoodId = input.nextLine();

                        Food selectedFood = foodRepository.findById(chooseFoodId).orElseThrow(() -> new NoSuchElementException("Makanan tidak ditemukan"));
                        
                        System.out.println("Masukkan quantity: ");
                        int orderQuantity = input.nextInt();

                        if(Integer.compare(orderQuantity, selectedFood.getStock())>0){
                            System.out.println("\nMaaf, stok terbatas. Silahkan pesan ulang!");
                            return;
                        }

                        cart.addItem(selectedFood, orderQuantity);
                        System.out.println("\n"+selectedFood.getName()+" Berhasil ditambahkan ke keranjang "+orderQuantity+" porsi !");    
                        
                    } catch (NoSuchElementException e) {
                        System.out.println("\nError : " + e.getMessage());
                    }
    }


    public void showCart(Cart cart,Scanner input){
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
                    
                    input.nextLine();
                    System.out.println("Checkout (Y/N): ");
                    String isCheckOut = input.nextLine();
                    // input.nextLine();

                    // TODO: akses pembayaran !

                    List<String> validInput = List.of("y", "ya", "yes");

                    if(validInput.contains(isCheckOut.trim().toLowerCase())){
                        System.out.println("masuk ke skema pembayaran");
                    } else {
                        System.out.println("\nBatal checkout, kembali ke menu utama!");
                    }


    }

    public  void checkout(Cart cart, Scanner input){
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

                    if(paymentMethod.equals("1".trim())){

                        CashPayment cash = new CashPayment(balance);
                        cash.Purchase(balance, totalPayment);

                    }else if(paymentMethod.equals("2".trim())){
                        QrisPayment cash = new QrisPayment(balance);
                        cash.Purchase(balance, totalPayment);
                    } else{
                        System.out.println("Metode pembayaran belum tersedia");
                        return;
                    }
                    
                    System.out.println("Terima kasih sudah membeli!\n");
    }
}
