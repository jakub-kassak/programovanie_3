import MarsColony.*;

import java.util.List;
import java.util.stream.Stream;

public class ColonyMain {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Market foodMarket = new Market("foodMarket", bank);
        Market workMarket = new Market("workMarket", bank);
        Factory f1 = new Factory("Factory #1",
                bank,
                new F2(),
                workMarket,
                foodMarket);
        Factory f2 = new Factory("Factory #2",
                bank,
                new F2(),
                workMarket,
                foodMarket);
        Colonist c1 = new Colonist("Michal",
                bank,
                new C2(),
                workMarket,
                foodMarket);
        Colonist c2 = new Colonist("Miroslav",
                bank,
                new C2(),
                workMarket,
                foodMarket);
        Colonist c3 = new Colonist("Richard",
                bank,
                new C2(),
                workMarket,
                foodMarket);
//        Colonist c4 = new Colonist("Roman",
//                bank,
//                new C2(),
//                workMarket,
//                foodMarket);

        Printer printer = new Printer();
        printer.registerObject(bank);
        printer.registerObject(foodMarket);
        printer.registerObject(workMarket);

        List<Thread> threads = Stream.of(printer, bank, f1, f2, c1, c2, c3)
                .map(Thread::new)
                .toList();

        threads.forEach(Thread::start);
        foodMarket.addOffer(0, 0);
        foodMarket.addOffer(0, 0);
        foodMarket.addOffer(0, 0);
        foodMarket.addOffer(0, 0);
    }

}
