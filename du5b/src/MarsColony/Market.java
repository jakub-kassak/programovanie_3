package MarsColony;

import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class Market {
    record T2<I, II> (I price, II seller){}
    private final String name;
    private final Bank bank;
    private final Queue<T2<Double, Integer>> offers = new PriorityQueue<>(Comparator.comparing(o -> o.price));
    private final Lock aLock = new ReentrantLock();
    private final Condition cond = aLock.newCondition();

    public Market(String name, Bank bank) {
        this.name = name;
        this.bank = bank;
    }

    private void awaitWhile(Predicate<Integer> p){
        while (p.test(offers.size())) {
            try {
                cond.await();
            } catch (InterruptedException ignored) {}
        }
    }

    public void addOffer(double price, int sellerId){
        aLock.lock();
        try {
            awaitWhile(x -> x > 5);
            offers.add(new T2<>(price, sellerId));
        } finally {
            cond.signal();
            aLock.unlock();
        }
    }

    public double buyBestOffer(int buyerId){
        T2<Double, Integer> bestO;
        aLock.lock();
        try {
            awaitWhile(x -> x == 0);
            bestO = offers.remove();
            bank.transaction(buyerId, bestO.seller, bestO.price);
        } finally {
            cond.signal();
            aLock.unlock();
        }
        return bestO.price;
    }

    public double averagePrice() {
        double avg = 1;
        aLock.lock();
        try {
            if (offers.size() > 0)
               avg =  offers
                    .stream()
                    .map(T2::price)
                    .reduce(0., Double::sum) / offers.size();
        } finally {
            aLock.unlock();
        }
        return avg;
    }

    public String toString(){
        String s;
        aLock.lock();
        try {
            s =  name + ": " + offers
                    .stream()
                    .map(t2 -> "(%d: %.2f)".formatted(t2.seller, t2.price))
                    .collect(Collectors.joining(", "));
        } finally {
            aLock.unlock();
        }
        return s;
    }
}
