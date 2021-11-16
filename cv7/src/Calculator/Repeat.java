package Calculator;

public class Repeat implements CalcOp {
    private final Calculator calculator;
    private final CalcOp op;
    private final int times;
    private Calculator.CalculatorMemento memento;

    public Repeat(Calculator calculator, CalcOp op, int times) {
        this.calculator = calculator;
        this.op = op.copy();
        this.times = times;
    }

    @Override
    public void execute() {
        memento = calculator.createMemento();
        for (int i = 0; i < times; i++) {
            op.execute();
        }
    }

    @Override
    public Repeat copy() {
        return new Repeat(calculator, op, times);
    }

    @Override
    public void undo() {
        if (memento != null)
            calculator.restoreMemento(memento);
    }
}
