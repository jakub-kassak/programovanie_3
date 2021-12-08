import java.util.List;

public class Whiskey extends CoffeDecorator{
    public Whiskey(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double getCost() {
        return super.getCost() + 2;
    }

    @Override
    public List<String> getIngredients(){
        List<String> ingredients = super.getIngredients();
        ingredients.add("Whiskey");
        return ingredients;
    }
}
