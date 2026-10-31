namespace Resto.Console.Interfaces;

public interface IPaymentMethod
{
    bool processPayment(decimal totalAmount);
    String getPaymentName();
}