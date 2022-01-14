package MarsColony;

public class C2 extends C1 {
    @Override
    public double estimate(double expenses, double balance) {
        double estimate = super.estimate(expenses, balance);
        if (balance < 0)
            estimate -= balance * 0.5;
        return estimate;
    }
}
