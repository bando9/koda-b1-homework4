using Resto.Console.Interfaces;

namespace Resto.Console.Models;

public abstract class BasePayment : IPaymentMethod
{
    public decimal Balance {get;protected set;}
    public abstract bool ProcessPayment(decimal totalAmount);
    public abstract String GetPaymentName();
    
}