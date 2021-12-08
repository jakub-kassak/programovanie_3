import java.util.List;

public class Milk extends CoffeDecorator{
    public Milk(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.3;
    }

    @Override
    public List<String> getIngredients(){
        List<String> ingredients = super.getIngredients();
        ingredients.add("Milk");
        return ingredients;
    }
}
