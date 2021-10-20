import java.util.List;

public class CoffeDecorator implements Coffee {
    private final Coffee coffee;

    public CoffeDecorator(Coffee coffee){
        this.coffee = coffee;
    }

    @Override
    public double getCost() {
        return coffee.getCost();
    }

    @Override
    public List<String> getIngredients() {
        return coffee.getIngredients();
    }
}
