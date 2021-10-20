import java.util.ArrayList;
import java.util.List;

public class Espresso implements Coffee {

    @Override
    public double getCost() {
        return 1;
    }

    @Override
    public List<String> getIngredients() {
        List<String> coffeeList = new ArrayList<>();
        coffeeList.add("Espresso");
        return coffeeList;
    }
}
