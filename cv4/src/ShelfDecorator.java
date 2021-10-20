public class ShelfDecorator implements Shelf {
    private Shelf shelf;

    public ShelfDecorator(Shelf shelf){
        this.shelf = shelf;
    }

    @Override
    public Coffee createEspresso() {
        return shelf.createEspresso();
    }

    @Override
    public Coffee addMilk(Coffee basis) {
        return shelf.addMilkFoam(basis);
    }

    @Override
    public Coffee addMilkFoam(Coffee basis) {
        return shelf.addMilkFoam(basis);
    }

    @Override
    public Coffee addWhiskey(Coffee basis) {
        return shelf.addWhiskey(basis);
    }

    @Override
    public Coffee addWater(Coffee basis) {
        return shelf.addWater(basis);
    }

    @Override
    public Coffee addWhippedCream(Coffee basis) {
        return shelf.addWhippedCream(basis);
    }
}
