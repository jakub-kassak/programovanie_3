package MarsColony;

public class F1 implements Strategy {
    @Override
    public double estimate(double expenses, double balance) {
        return Math.max(1, expenses * 1.1 / 6);
    }
}
