package MarsColony;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class Colonist implements Runnable{
    private final String name;
    private final Bank bank;
    private final Strategy strat;
    private final Market wMarket;
    private final Market fMarket;
    private final int id;

    public Colonist(String name, Bank bank, Strategy strat, Market workMarket, Market foodMarket) {
        this.name = name;
        this.bank = bank;
        this.strat = strat;
        this.wMarket = workMarket;
        this.fMarket = foodMarket;
        id = bank.addAccount(name, 100);
    }

    @Override
    public void run() {
        while (true){
            double wPrice = strat.estimate(fMarket.averagePrice(), bank.balance(id));
            CompletableFuture.runAsync(() ->  wMarket.addOffer(wPrice, id));
            fMarket.buyBestOffer(id);
            sleep(100L);
        }
    }

    private void sleep(Long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignored) {}
    }
}
