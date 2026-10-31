using Resto.Console.Models;

System.Console.WriteLine("\n==================================");
System.Console.WriteLine("SELAMAT DATANG DI BAKSO SATELIT");
System.Console.WriteLine("==================================");

bool isRunningApp = true;

OrderService orderService = new();

Cart cart = new();


while (isRunningApp)
{

    string[] menu = ["1. Lihat Menu Makanan","2. Cari menu makanan","3. Keranjang","4. Checkout","5. Exit"];

    System.Console.WriteLine("\n============ MENU UTAMA ============\n");
    foreach(string m in menu)
    {
        System.Console.WriteLine(m);
    }


    System.Console.WriteLine("Silahkan pilih menu (1-4): ");
    string? choiceMenu = Console.ReadLine();

    switch (choiceMenu)
    {
        case "1":
            orderService.ShowAndOrderFood(cart);
            break;
        case "2":
            orderService.SearchFoodByName();
            break;
        case "3":
            orderService.ShowCart(cart);
            break;
        case "4":
            orderService.Checkout(cart);
            break;
        case "5":
            System.Console.WriteLine("Exit");
            isRunningApp = false;
            break;
        default:
            System.Console.WriteLine("\nPilihan tidak valid, pilih menu (1-4)\n");
            break;    
    }



}