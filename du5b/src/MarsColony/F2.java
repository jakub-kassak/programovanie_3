package MarsColony;

public class F2 extends F1 {
    @Override
    public double estimate(double expenses, double balance) {
        double estimate = super.estimate(expenses, balance);
        if (balance < 0)
            estimate -= balance * 0.2;
        return estimate;
    }
}
