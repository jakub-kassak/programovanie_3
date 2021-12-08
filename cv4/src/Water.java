import java.util.List;

public class Water extends CoffeDecorator{
    public Water(Coffee coffee) {
        super(coffee);
    }

    @Override
    public List<String> getIngredients(){
        List<String> ingredients = super.getIngredients();
        ingredients.add("Water");
        return ingredients;
    }
}
