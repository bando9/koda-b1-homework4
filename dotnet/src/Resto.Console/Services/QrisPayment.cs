using Resto.Console.Interfaces;

namespace Resto.Console.Services;

public class QrisPayment : IPaymentMethod
{
    public bool processPayment(decimal totalAmount)
    {
        return true;
    }

    public string getPaymentName()
    {
        return "Pembayaran Qris berhasil !"; 
    }
}