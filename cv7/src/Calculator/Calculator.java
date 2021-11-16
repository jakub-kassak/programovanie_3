package Calculator;

public class Calculator {
    private int acc;

    void setAcc(int acc) {
        this.acc = acc;
    }

    public int getAcc() {
        return acc;
    }

    void add(int x){
        acc += x;
    }

    void multiply(int x){
        acc *= x;
    }

    public CalculatorMemento createMemento(){
        return new CalculatorMemento(acc);
    }

    public void restoreMemento(CalculatorMemento memento){
        if (memento != null)
            this.acc = memento.acc;
    }

    static class CalculatorMemento{
        private final int acc;

        private CalculatorMemento(int acc) {
            this.acc = acc;
        }
    }
}
