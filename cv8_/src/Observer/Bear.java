package Observer;

public class Bear implements Observer<StockExchange>{
    private double rate;

    public Bear(double rate) {
        this.rate = rate;
    }

    public void changeOfRate(double rate){
        if (this.rate < rate)
            System.out.println("Bears lost and are very angry!");
        else
            System.out.println("Bears profited.");
        this.rate = rate;
    }

    @Override
    public void update(StockExchange se) {
        changeOfRate(se.getRate());
    }
}
