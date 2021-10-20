import java.util.List;

public class MilkFoam extends CoffeDecorator{
    public MilkFoam(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.5;
    }

    @Override
    public List<String> getIngredients(){
        List<String> ingredients = super.getIngredients();
        ingredients.add("MilkFoam");
        return ingredients;
    }
}
