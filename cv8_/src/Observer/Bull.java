package Observer;

public class Bull implements Observer<StockExchange>{
    private double rate;

    public Bull(double rate) {
        this.rate = rate;
    }

    private void changeOfRate(double rate){
        if (this.rate < rate)
            System.out.println("Bulls are enjoying current growth.");
        else
            System.out.println("Bulls are sad!");
        this.rate = rate;
    }

    @Override
    public void update(StockExchange se) {
        changeOfRate(se.getRate());
    }
}
