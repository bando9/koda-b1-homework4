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

    public void RemoveItem(Food food)
    {
        CartItem? deletedItem = Items.FirstOrDefault(item => item.Item.Id == food.Id);


       if(deletedItem is null)
        {
            System.Console.WriteLine("Maaf, orderan emg blm dipesen.");
        }

        food.AddStock(deletedItem.Quantity);

        Items.Remove(deletedItem);
        System.Console.WriteLine($"{deletedItem.Item.Name} berhasil dihapus dari pesanan");
    }

    public List<CartItem> getItems(){
        return Items;
    }


}