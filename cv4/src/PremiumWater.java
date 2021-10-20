import java.util.List;

public class PremiumWater extends CoffeDecorator{
    public PremiumWater(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double getCost() {
        return super.getCost() + 2;
    }

    @Override
    public List<String> getIngredients(){
        List<String> ingredients = super.getIngredients();
        ingredients.add("PremiumWater");
        return ingredients;
    }
}
