package Calculator;

public interface CalcOp {

    void undo();
    void execute();
    CalcOp copy();

}
