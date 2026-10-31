namespace Resto.Console.Models;

public class Food
{
 public String? Id {get;private set;}
    public String? Name {get;private set;}
    public decimal Price {get; private set;}
    public int Stock {get; private set;}

    public Food(String id, String name, decimal price, int stock)
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

    public void ReduceStock(int quantity)
    {
        if (quantity <= 0)
        {
            System.Console.WriteLine("Quantity gabisa kurang dari 0");
            return;
        }

        Stock-=quantity;
    }

    public int AddStock(int quantity)
    {
        if (quantity <= 0)
        {
            System.Console.WriteLine("Quantity gabisa kurang dari 0");
        }
        return Stock+=quantity;
    }


    public String getDetails()
    {
        return $"{Id}. {Name} - {Price} (Stok:{Stock})";
    }
}