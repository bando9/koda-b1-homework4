
namespace Resto.Console.Services;

using Resto.Console.Models;
using System;
public class CashPayment:BasePayment
{
    public CashPayment(decimal balance)
    {
        if (balance <= 0)
        {
            System.Console.WriteLine("Maaf gabisa, masak saldo kurang dari 0.");
        }

        Balance=balance;
    }
    public override bool ProcessPayment(decimal totalAmount)
    {

        if(Balance < totalAmount)
        {
            System.Console.WriteLine("Maaf gabisa bayar, uang tunia kurang.");
            return false;
        }
        decimal change = Balance - totalAmount;

        string isPay = Console.ReadLine();

        if (string.Equals(isPay,"ya",StringComparison.OrdinalIgnoreCase) || string.Equals(isPay,"y",StringComparison.OrdinalIgnoreCase) || string.Equals(isPay,"yes",StringComparison.OrdinalIgnoreCase))
        {
            GetPaymentName();
        }
        else
        {
            System.Console.WriteLine("transaksi batal. balik ke menu utama");
        }

        return true;
    }

    public override string GetPaymentName()
    {
        return "Cash"; 
    }
    
}



