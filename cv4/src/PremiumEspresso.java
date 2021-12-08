import java.util.ArrayList;
import java.util.List;

public class PremiumEspresso implements Coffee {
    @Override
    public double getCost() {
        return 10;
    }

    @Override
    public List<String> getIngredients() {
        List<String> coffeeList = new ArrayList<>();
        coffeeList.add("PremiumEspresso");
        return coffeeList;
    }
}
