package com.homework4.model;

import com.homework4.interfaces.Payment;

public class QrisPayment implements Payment {
    private int balance;

    public QrisPayment(int balance){
        if(balance<0){
            throw new IllegalArgumentException("balance must be more than 0");
        }
        this.balance = balance;
    }

    public void Purchase(int balance, int amount){
        int refund = balance-amount;

        System.out.println("Kembalian  \tRp"+refund);
        System.out.println("\nPembayaran QRIS berhasil");
    }
}
