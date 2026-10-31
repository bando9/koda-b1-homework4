namespace Resto.Console.Models;
using System;

public class OrderService
{
    public void ShowAndOrderFood()
    {
        System.Console.WriteLine("\ntampilan menu dan order");
    }

    public void ShowCart()
    {
        System.Console.WriteLine("\n==================================");
        System.Console.WriteLine("             KERANJANG            ");
        System.Console.WriteLine("==================================");

        bool isCartOption  = true;

        while (isCartOption)
        {
            string[] cartOption = ["1. Ubah jumlah porsi","2. Hapus orderan","3. Lanjut checkout","4. Kembali ke Menu Utama"];

            System.Console.WriteLine("\nOpsi Keranjang:");
            foreach(string option in cartOption)
            {
                System.Console.WriteLine(option);
            }

            System.Console.WriteLine("Pilih opsi (1-4)");
            string? SelectedCartOption = Console.ReadLine();

            switch (SelectedCartOption)
            {
                case "1":
                    System.Console.WriteLine(cartOption[0]);
                    break;
                case "2":
                    System.Console.WriteLine(cartOption[1]);
                    break;
                case "3":
                    System.Console.WriteLine(cartOption[2]);
                    break;
                case "4":
                    System.Console.WriteLine(cartOption[3]);
                    isCartOption=false;
                    break;
                default:
                    System.Console.WriteLine("Pilihan menu tidak ada.");
                    break;
            }
    
        }

    }

    public void Checkout()
    {
        System.Console.WriteLine("\ntampilan checkout");
    }

    public void SearchFoodByName()
    {
        System.Console.WriteLine("\ntampilan search menu makanan");
    }

    
}