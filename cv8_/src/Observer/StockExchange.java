package Observer;

import java.util.LinkedList;
import java.util.List;

public class StockExchange {
    private double rate;
    List<Observer<StockExchange>> observers = new LinkedList<>();

    public void addObserver(Observer<StockExchange> o){
        observers.add(o);
    }

    public void removeObserver(Observer<StockExchange> o){
        observers.remove(o);
    }

    public void setRate(double rate) {
        if (rate != this.rate) {
            this.rate = rate;
            observers.forEach(o -> o.update(this));
        }
    }

    public double getRate() {
        return rate;
    }
}
