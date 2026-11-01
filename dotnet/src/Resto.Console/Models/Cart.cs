namespace Resto.Console.Models;

public class Cart
{
    public List<CartItem> Items {get; private set;} = new List<CartItem>();

    public decimal TotalAmount()
    {
        return Items.Sum(x => x.Subtotal());
    }

    public void AddItem(Food food, int quantity)
    {
        if(quantity<= 0)
        {
            System.Console.WriteLine("Maaf, quantity harus lebih dari 0");
            return;
        }

        if(food is null)
        {
            System.Console.WriteLine("Maaf, menu belum tersedia");
            return;
        }

        if (quantity > food.Stock)
        {
            System.Console.WriteLine($"Maaf stok terbatas, stock tersisa: {food.Stock}");
            System.Console.WriteLine("Order gagal, Silahkan input pesanan lagi.");
            return;
        }

        Items.Add(new CartItem(food, quantity));
        food.ReduceStock(quantity);
    }
    

    public void Clear()
    {
        Items.Clear();
    }

    public bool IsEmpty()
    {
        if (!Items.Any())
        {
            return true;
        } 

        return false;
    }

    public void RemoveItem(int index)
    {
        if(index<0 || index > Items.Count())
        {
            throw new ArgumentOutOfRangeException("makanan yg kmu masukkan emg blm dipesen.");
        }

        Items.RemoveAt(index-1);
    }

    public List<CartItem> getItems(){
        return Items;
    }


}