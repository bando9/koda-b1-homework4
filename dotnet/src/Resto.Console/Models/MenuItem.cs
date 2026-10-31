namespace Resto.Console.Models;

public abstract class MenuItem
{
    public String? Id {get;private set;}
    public String? Name {get;private set;}
    public decimal Price {get; private set;}
    public int Stock {get; private set;}

    public MenuItem(String id, String name, decimal price, int stock)
    {
        Id =id;
        Name=name;
        Price=price;
        Stock=stock;
    }

    public void UpdateStock(int newStock)
    {
        Stock=newStock;
    }

    public abstract String getDetails();

} 