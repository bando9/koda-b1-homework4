using Resto.Console.Models;

namespace Resto.Console.Services;

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

        if(Balance <= totalAmount)
        {
            System.Console.WriteLine("Maaf gabisa bayar, saldomu dikit.");
        }
        return true;
    }

    public override string GetPaymentName()
    {
        return "Pembayaran Cash berhasil !"; 
    }
    
}