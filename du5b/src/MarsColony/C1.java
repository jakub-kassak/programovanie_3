package MarsColony;

public class C1 implements Strategy {
    @Override
    public double estimate(double expenses, double balance) {
        return expenses * 1.1;
    }
}
