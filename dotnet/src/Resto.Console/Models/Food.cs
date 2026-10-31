namespace Resto.Console.Models;

public class Food : MenuItem
{

    public Food (string id,string name, decimal price,int stock):base(id,name,price,stock)
    {
     
    }
    public override String getDetails()
    {
        return $"{Id}. {Name} - {Price} (Stok:{Stock})";

        
    }
}