public interface Shelf {

    Coffee createEspresso();
    Coffee addMilk(Coffee basis);
    Coffee addMilkFoam(Coffee basis);
    Coffee addWhiskey(Coffee basis);
    Coffee addWater(Coffee basis);
    Coffee addWhippedCream(Coffee basis);

}
