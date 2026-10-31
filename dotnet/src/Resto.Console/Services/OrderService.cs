namespace Resto.Console.Models;
using System;

public class OrderService
{
    public void ShowAndOrderFood(Cart cart)
    {
        try
        {
            System.Console.WriteLine("\n==================================");
            System.Console.WriteLine("         MENU MAKANAN         ");
            System.Console.WriteLine("==================================");

            OrderService.InitFoodsAndShow();

            System.Console.WriteLine("==================================");

            List<Food> foods = OrderService.foods();

            
            System.Console.WriteLine("Pilih makanan (id): ");
            String? selectFoodId = Console.ReadLine();

            Food? foundFood = foods.FirstOrDefault(food => food.Id == selectFoodId);

            if(foundFood is null)
            {
                System.Console.WriteLine("Maaf, menu belum tersedia");
                return;
            }
            
            System.Console.WriteLine($"Mau berapa porsi {foundFood?.Name}: ");
            int inputQuantity = int.Parse(Console.ReadLine());


            if(inputQuantity > 0 && inputQuantity <= foundFood?.Stock)
            {
                System.Console.WriteLine($"{foundFood.Name} sebanyak {inputQuantity} porsi sudah dicatet!");
            }

            cart.addItem(foundFood,inputQuantity);

        }
        catch (InvalidOperationException)
        {
            throw new KeyNotFoundException("Maaf, menu belum tersedia");
        }
    }

    public void ShowCart(Cart cart)
    {

        if (cart.isEmpty())
        {
            System.Console.WriteLine("Keranjang masih kosong, monggo order dulu gan.");
            return;
        }
        else
        {
            System.Console.WriteLine("\n==================================");
            System.Console.WriteLine("             KERANJANG            ");
            System.Console.WriteLine("==================================\n");

            System.Console.WriteLine("----------------------------------");
            System.Console.WriteLine("TOTAL PEMBAYARAN = Rp."+cart.TotalAmount()+"\n");
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
    }

    public void Checkout()
    {
        System.Console.WriteLine("\ntampilan checkout");
    }

    public void SearchFoodByName()
    {
        System.Console.WriteLine("\ntampilan search menu makanan");
    }

    public static void InitFoodsAndShow()
    {
        List<Food> foods = OrderService.foods();

        foreach(Food food in foods)
        {
            System.Console.WriteLine($"{food.Id}. {food.Name} - {food.Price:N0} (Stok: {food.Stock})");
        }
    }

    public static List<Food> foods()
    {
        var foods = new List<Food>
        {
            new Food("1", "Bakso Urat", 13000, 10),
            new Food("2", "Bakso Tetelan", 16000, 20),
            new Food("3", "Bakso Rudal", 20000, 5),
            new Food("4", "Bakso Iga", 18000, 15),
            new Food("5", "Bakso Telor", 20000, 50)
        };
        return foods;
    }
}