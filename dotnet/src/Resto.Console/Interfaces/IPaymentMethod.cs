namespace Resto.Console.Interfaces;

public interface IPaymentMethod
{
    bool ProcessPayment(decimal totalAmount);
    String GetPaymentName();
}