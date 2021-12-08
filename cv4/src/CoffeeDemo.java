import java.util.ArrayList;
import java.util.List;

public class CoffeeDemo {

    public static void main(String[] args) {
        Shelf shelf = new Logger(new PremiumShelf(), "logPremium.txt");
        Barista barista = new Barista(shelf);
        barista.getCoffee("americano");
        barista.getCoffee("irish coffe");
        barista.getCoffee("vienna coffe");
        barista.getCoffee("latte");
    }

}
