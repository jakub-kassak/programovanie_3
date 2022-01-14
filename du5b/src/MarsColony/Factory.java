package MarsColony;

public class Factory implements Runnable{
    private final String name;
    private final Bank bank;
    private final Strategy strat;
    private final Market wMarket;
    private final Market fMarket;
    private final int id;

    public Factory(String name, Bank bank, Strategy strat, Market workMarket, Market foodMarket) {
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
            double wPrice = 0;
            for (int i = 0; i < 5; i++) {
                wPrice += wMarket.buyBestOffer(id);
            }
            double pPrice = strat.estimate(wPrice, bank.balance(id));
            for (int i = 0; i < 6; i++) {
                fMarket.addOffer(pPrice, id);
            }
        }
    }
}
