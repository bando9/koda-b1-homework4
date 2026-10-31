package com.homework4.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.homework4.model.Food;
import com.homework4.model.Cart;
import com.homework4.model.CartItem;
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
        if(cart.isEmpty()){
             System.out.println("\nKeranjang kosong. Pesan terlebih dahulu!");
             input.nextLine();
            return;
        } 

        boolean inCartMenu = true;

        while (inCartMenu) {
            
        

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
                    System.out.println("----------------------------------");
                    System.out.println("TOTAL PEMBAYARAN = Rp."+cart.calculateTotal());
                    System.out.println("==================================");

                    System.out.println();
                    System.out.println();

                    System.out.println("\nOpsi Keranjang:");
                    System.out.println("1. Ubah jumlah porsi");
                    System.out.println("2. Hapus orderan");
                    System.out.println("3. Lanjut checkout");
                    System.out.println("4. Kembali ke Menu Utama");

                    System.out.println("Pilih opsi (1-4)");
                    // input.nextLine();
                    String choice = input.nextLine();
                    switch (choice) {
                        case "1":
                            System.out.println("updateQuantity(cart, input)");;
                            break;
                        case "2":
                            removeOrder(cart, input);
                            break;
                        case "3":
                            checkout(cart, input);
                            inCartMenu = false;
                            break;
                        case "4":
                            inCartMenu = false;

                        default:
                            System.out.println("Pilihan tidak valid!");
                            break;
                    }
                
                }

    }

    public  void checkout(Cart cart, Scanner input){
        if(cart.isEmpty()){
            System.out.println("\nKeranjang kosong. Pesan terlebih dahulu!");
            input.nextLine();
            return;
        } 

            int totalPayment = cart.calculateTotal();
                    System.out.println();
                    System.out.println("Metode Pembayaran: ");
                    System.out.println("1. Cash");
                    System.out.println("2. QRIS");
                    
                    System.out.println("Pilih metode: ");
                    String paymentMethod = input.nextLine();

                    System.out.println("Masukkan uang");
                    int balance = input.nextInt();

                    if(balance <= 0 || balance < totalPayment){
                        System.out.println("\nMaaf saldo tidak mencukupi!");
                    } else {
                        PaymentService paymentService = new PaymentService(cart, totalPayment, balance, paymentMethod);
                        paymentService.showReceipt();
                        cart.clearCart();
                    }
        
    }




    // private void updateQuantity(Cart cart, Scanner input){
    //     System.out.println("\nMasukkan ID makanan dari keranjang yg mau diubah:");
    //     String foodId = input.nextLine();

    //     System.out.println("Masukkan jumlah porsi baru: ");
    //     try {

    //         int newQuantity = input.nextInt();
    //         boolean updated = cart.updateQuantity(foodId, newQuantity);

    //         if(updated){
    //             System.out.println("Berhasil memperbarui jumlah porsi!");
    //         }else{
    //             System.out.println("Makanan dg ID tersebut tidak ada.");
    //         }
    //     } catch (NumberFormatException e) {
    //         System.out.println("Input jumlah porsi harus berupa angka!");
    //     }
    // }


    // TODO: hapus pesanan

    public void removeOrder(Cart cart, Scanner input){
        if(cart.isEmpty()){
            System.out.println("\nKeranjang kosong. Pesan terlebih dahulu!");
            input.nextLine();
            return;
        } 

        System.out.println("Masukkan id makanan yg mau dihapus:");
        int removeFoodId = input.nextInt() -1;

        try {
            
            
            if(removeFoodId>=0 || removeFoodId<cart.sizeItems()){
                cart.removeItem(removeFoodId);
            } else{
                System.out.println("Id makanan tidak ditemukan");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID makanan tidak ditemukan.");
        }
    }
}
