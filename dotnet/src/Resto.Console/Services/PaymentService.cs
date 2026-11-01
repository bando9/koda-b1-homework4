using Resto.Console.Interfaces;
using Resto.Console.Models;

namespace Resto.Console.Services;

public class PaymentService
{
    
    public IPaymentMethod ChoosePaymentMethod(string option, decimal balance)
    {
        if (option.Equals("1"))
        {
            return new CashPayment(balance);
        } else if (option.Equals("2"))
        {
            return new QrisPayment(balance);
        }
        else
        {
            return null;
        }
    } 

    public bool Process(IPaymentMethod payment, decimal totalAmount)
    {
        return payment.ProcessPayment(totalAmount);
    }

    public void ShowReceipt(Cart cart, decimal balance)
    {
         System.Console.WriteLine("=============== RECEIPT ===============\n");

        int j = 0;
        foreach(CartItem item in cart.getItems())
        {
            j++;
            System.Console.WriteLine($"{j}. {item.Item.Name} ({item.Quantity}x) = Rp.{item.Subtotal():N0}");
        }

        
        System.Console.WriteLine("\n---------------------------------------");
        System.Console.WriteLine($"Total \t\tRp.{cart.TotalAmount():N0}");
        System.Console.WriteLine($"Bayar \t\tRp.{balance}");

        
    }

    
}