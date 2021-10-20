import java.util.List;

public class WhippedCream extends CoffeDecorator{
    public WhippedCream(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double getCost() {
        return super.getCost() + 1;
    }

    @Override
    public List<String> getIngredients(){
        List<String> ingredients = super.getIngredients();
        ingredients.add("WhippedCream");
        return ingredients;
    }
}
