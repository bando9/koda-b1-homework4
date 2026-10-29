package com.homework4.service;

import com.homework4.model.CartItem;
import com.homework4.model.Food;
import com.homework4.model.Cart;

public class PaymentService {
    private Cart cart;
    private int totalPayment;
    private int balance;
    private String paymentMethod;

    public PaymentService(Cart cart, int totalPayment, int balance, String paymentMethod){
        this.cart=cart;
        this.totalPayment=totalPayment;
        this.balance=balance;
        this.paymentMethod=paymentMethod;
    }

    public void showReceipt(){
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
