namespace Resto.Console.Models;
using System;
using Resto.Console.Services;

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
            
            System.Console.WriteLine("Pilih makanan (id): ");
            String? selectFoodId = Console.ReadLine();

            List<Food> foods = OrderService.foods();
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
                cart.AddItem(foundFood,inputQuantity);
                System.Console.WriteLine($"{foundFood.Name} sebanyak {inputQuantity} porsi sudah dicatet!");
            } else
            {
                System.Console.WriteLine("Maaf, stok terbatas gan.");
            }
        }
        catch (InvalidOperationException)
        {
            throw new KeyNotFoundException("Maaf, menu belum tersedia");
        }
    }

    public void ShowCart(Cart cart)
    {

        if (cart.IsEmpty())
        {
            System.Console.WriteLine("Keranjang masih kosong, monggo order dulu gan.");
            return;
        }
        else
        {
            List<Food> foods = OrderService.foods();

            System.Console.WriteLine("\n==================================");
            System.Console.WriteLine("             KERANJANG            ");
            System.Console.WriteLine("==================================\n");

            foreach(CartItem item in cart.getItems())
            {
                System.Console.WriteLine($"{item.Item.Name} {item.Quantity} porsi x Rp. {item.Item.Price:N0} Rp. {item.Subtotal():N0}");
            }

            System.Console.WriteLine("\n\n----------------------------------");
            System.Console.WriteLine("TOTAL PEMBAYARAN = Rp."+cart.TotalAmount());
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
                        // TODO: delete order
                        OrderService.RemoveOrder(foods,cart);
                        break;
                    case "3":
                        Checkout(cart);
                        isCartOption=false;
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

    public void Checkout(Cart cart)
    {
         if (cart.IsEmpty())
        {
            System.Console.WriteLine("Keranjang masih kosong, monggo order dulu gan.");
            return;
        }
        else
        {
           System.Console.WriteLine("Metode Pembayaran: ");
           System.Console.WriteLine("1. Cash");
           System.Console.WriteLine("2. QRIS");

           System.Console.WriteLine("Pilih metode pembayaran: ");

           string? selectedPayment = Console.ReadLine();

            switch (selectedPayment)
            {
                case "1":
                    System.Console.WriteLine("\nMasukkan nominal saldo:");
                    decimal balance = int.Parse(Console.ReadLine());

                    if(balance<0 || balance < cart.TotalAmount())
                    {
                        System.Console.WriteLine("Maaf saldo tidak cukup.");
                        return;
                    }

                    CashPayment cash = new CashPayment(balance);
                    cash.ProcessPayment(cart.TotalAmount());

                    System.Console.WriteLine($"(validasi) Beneran bayar dg total Rp. {cart.TotalAmount()}?");
                    bool isPay = bool.Parse(Console.ReadLine());
                    
                    if (isPay)
                    {
                        cash.GetPaymentName();
                    }

                    break;

                case "2":
                    System.Console.WriteLine("\nMasukkan nominal saldo:");
                    decimal balanceUser = int.Parse(Console.ReadLine());

                    if(balanceUser<0 || balanceUser < cart.TotalAmount())
                    {
                        System.Console.WriteLine("Maaf saldo tidak cukup.");
                        return;
                    }

                    QrisPayment qris = new QrisPayment(balanceUser);
                    qris.ProcessPayment(cart.TotalAmount());

                    System.Console.WriteLine($"(validasi) Beneran bayar dg total Rp. {cart.TotalAmount()}?");
                    bool isPayCash = bool.Parse(Console.ReadLine());
                    
                    if (isPayCash)
                    {
                        qris.GetPaymentName();
                    }                    
                    break;
                default:
                    System.Console.WriteLine("Metode pembayaran blm tersedia.");
                    break;
            }

                
        }
    }

    public async Task SearchFoodByName()
    {
        List<Food> foods = OrderService.foods();

        System.Console.WriteLine("\n==================================");
        System.Console.WriteLine("            CARI MAKANAN            ");
        System.Console.WriteLine("==================================\n");
        
        var queryService = new FoodQueryService(foods);

        System.Console.WriteLine("masukkan nama makanan:");
        string? findFood = Console.ReadLine();

        Food? result = await queryService.FindFoodAsync(findFood);


        if(result is not null)
        {
            System.Console.WriteLine(result.getDetails() );
        }
        else
        {
            System.Console.WriteLine("Makanan blm ada di menu");
        }

    }

    public static void InitFoodsAndShow()
    {
        List<Food> foods = OrderService.foods();

        foreach(Food food in foods)
        {
            System.Console.WriteLine($"{food.Id}. {food.Name} - Rp.{food.Price:N0} (Stok: {food.Stock})");
        }
    }

    public static List<Food> foods()
    {
        var foods = new List<Food>
        {
            new Food("1", "Bakso Urat", 13_000, 10),
            new Food("2", "Bakso Tetelan", 16_000, 20),
            new Food("3", "Bakso Rudal", 20_000, 5),
            new Food("4", "Bakso Iga", 18_000, 15),
            new Food("5", "Bakso Telor", 20_000, 50)
        };
        return foods;
    }


    // TODO:1. Ubah jumlah porsi
    // public void UpdateQuantityFood(){} 
    // TODO:2. Hapus orderan
    public static void RemoveOrder(List<Food> foods, Cart cart)
    {
        System.Console.WriteLine("Makanan apa yg mau di cancel (id): ");
        string? foodId = Console.ReadLine();

        Food? foundFood = foods.FirstOrDefault(food => food.Id == foodId);

        if(foundFood is null)
        {
            System.Console.WriteLine("Makanan yg mau dihapus emang blm dipesen (ERROR).");
            return;
        }

        cart.RemoveItem(foundFood);

    }

}