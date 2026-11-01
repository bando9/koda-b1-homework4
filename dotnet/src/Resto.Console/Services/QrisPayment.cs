using Resto.Console.Models;

namespace Resto.Console.Services;

public class QrisPayment:BasePayment
{
    public QrisPayment(decimal balance)
    {
        if (balance <= 0)
        {
            System.Console.WriteLine("Maaf gabisa, masak saldo kurang dari 0.");
            return;
        }

        Balance=balance;
    }
    public override bool ProcessPayment(decimal totalAmount)
    {

        if(Balance < totalAmount)
        { 
            System.Console.WriteLine("Maaf gabisa bayar, saldo QRIS-mu dikit.");
            return false;
        }

        Balance -= totalAmount;
        System.Console.WriteLine("Pembayaran via QRIS berhasil");

        return true;
    }

    public override string GetPaymentName()
    {
        return "Qris"; 
    }
    
}