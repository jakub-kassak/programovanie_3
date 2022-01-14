package MarsColony;

import javax.management.InstanceNotFoundException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class Bank implements Runnable{
    private final AtomicInteger counter = new AtomicInteger(0);
    private final ConcurrentMap<Integer, AtomicReference<Double>> accounts = new ConcurrentHashMap<>();
    private final ConcurrentMap<Integer, String> names = new ConcurrentHashMap<>();

    public Bank(){
        addAccount("bank", 0);
    }

    public int addAccount(String name, double initialBalance){
        int accountId = counter.getAndAdd(1);
        accounts.put(accountId, new AtomicReference<>(initialBalance));
        names.put(accountId, name);
        return accountId;
    }

    public double balance(int id){
        return accounts.get(id).get();
    }

    public void transaction(int from, int to, double amount){
        accounts.get(from).accumulateAndGet(-amount, Double::sum);
        accounts.get(to).accumulateAndGet(amount, Double::sum);
    }

    @Override
    public String toString() {
        return "bank (%.2f):".formatted(accounts.get(0).get()) + accounts.keySet()
                .stream()
                .filter(x ->x > 0)
                .map(id -> "%n%s: %.2f".formatted(names.get(id), accounts.get(id).get()))
                .collect(Collectors.joining());
    }

    @Override
    public void run() {
        AtomicReference<Double> atFine = new AtomicReference<>(0.0);
        while (true) {
            for (Map.Entry<Integer, AtomicReference<Double>> e : accounts.entrySet()) {
                e.getValue().updateAndGet(balance -> {
                    if (balance >= 0)
                        return balance;
                    double fine = -balance * 0.01;
                    atFine.set(fine);
                    return balance - fine;
                });
                accounts.get(0).accumulateAndGet(atFine.get(), Double::sum);
            }
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException ignored) {
            }
        }
    }
}
