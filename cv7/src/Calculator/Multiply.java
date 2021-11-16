package Calculator;

public class Multiply implements CalcOp {
    private final Calculator c;
    private final int x;
    private Calculator.CalculatorMemento memento;

    public Multiply(Calculator c, int x) {
        this.c = c;
        this.x = x;
    }

    @Override
    public void execute() {
        memento = c.createMemento();
        c.multiply(x);
    }

    @Override
    public Multiply copy() {
        return new Multiply(c, x);
    }

    @Override
    public void undo() {
        c.restoreMemento(memento);
    }
}
