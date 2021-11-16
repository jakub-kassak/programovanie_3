package Calculator;

public class Add implements CalcOp {
    Calculator calculator;
    public int value;

    public Add(Calculator calculator, int value) {
        this.calculator = calculator;
        this.value = value;
    }

    @Override
    public void execute() {
        calculator.add(value);
    }

    @Override
    public Add copy() {
        return new Add(calculator, value);
    }

    @Override
    public void undo() {
        calculator.add(-value);
    }
}
