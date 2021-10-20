public class PremiumShelf implements Shelf {
    @Override
    public Coffee createEspresso() {
        return new PremiumEspresso();
    }

    @Override
    public Coffee addMilk(Coffee basis) {
        return new Milk(basis);
    }

    @Override
    public Coffee addMilkFoam(Coffee basis) {
        return new MilkFoam(basis);
    }

    @Override
    public Coffee addWhiskey(Coffee basis) {
        return new Whiskey(basis);
    }

    @Override
    public Coffee addWater(Coffee basis) {
        return new PremiumWater(basis);
    }

    @Override
    public Coffee addWhippedCream(Coffee basis) {
        return new WhippedCream(basis);
    }
}
