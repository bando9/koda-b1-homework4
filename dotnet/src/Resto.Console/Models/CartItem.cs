namespace Resto.Console.Models;

public class CartItem
{
    public Food Item {get;set;}
    public int Quantity {get;set;}

    public CartItem(Food item,int quantity)
    {
        Item=item;
        Quantity=quantity;
    }

    public decimal Subtotal()
    {
        return Item.Price* Quantity;
    }
}