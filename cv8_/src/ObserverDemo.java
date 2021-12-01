import Observer.Bear;
import Observer.Bull;
import Observer.StockExchange;

import java.util.Scanner;

public class ObserverDemo {
    public static void main(String[] args) {
        StockExchange stockExchange = new StockExchange();
        stockExchange.setRate(0);
        stockExchange.addObserver(new Bull(stockExchange.getRate()));
        stockExchange.addObserver(new Bear(stockExchange.getRate()));
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            double rate = sc.nextDouble();
            stockExchange.setRate(rate);
        }
    }
}
