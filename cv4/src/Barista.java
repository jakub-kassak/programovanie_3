public class Barista {
    private final Shelf shelf;

    Barista(Shelf shelf){
        this.shelf = shelf;
    }

    Coffee getCoffee(String name){
        Coffee coffee = shelf.createEspresso();
        switch (name){
            case "espresso":
                return coffee;
            case "americano":
                return shelf.addWater(coffee);
            case "latte":
                coffee = shelf.addMilk(coffee);
                return shelf.addMilkFoam(coffee);
            case "irish coffe":
                coffee = shelf.addWhiskey(coffee);
            case "vienna coffe":
                return shelf.addWhippedCream(coffee);
        }
        throw new NoSuchFieldError();
    }
}
