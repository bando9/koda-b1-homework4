namespace Resto.Console.Models;

public class Cart
{
    public List<CartItem> Items {get; private set;} = new List<CartItem>();

    public decimal TotalAmount()
    {
        return Items.Sum(x => x.Subtotal());
    }

    public void Clear()
    {
        Items.Clear();
    }
}