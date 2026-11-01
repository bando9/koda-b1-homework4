using Resto.Console.Models;

public class FoodQueryService
{
    private readonly IReadOnlyList<Food> _foods;

    public FoodQueryService(IReadOnlyList<Food> foods)
    {
        _foods = foods;
    }
    public async Task<Food?> FindFoodAsync(
        string productName
    )
    {
        await Task.Delay(300);

        return _foods.FirstOrDefault(
            food => food.Name.Contains(productName, StringComparison.OrdinalIgnoreCase)
        );
    }
}