import Calculator.Calculator;
import Calculator.*;

public class CommandDemo {
    public static void main(String[] args) {
        //Command mock = new MockTask(1, 1);
        //mock.execute();
        //Planner planner = new Planner();
        //Random random = new Random();
        //
        //for (int i = 1; i <= 50; i++) {
        //    planner.addCommand(new MockTask(i, i), i);
        //}
        //planner.executeAll();

        /*
        Calculator.Calculator calculator = new Calculator.Calculator();
        Calculator.Operation add2 = new Calculator.Add(calculator, 2);
        add2.execute();
        System.out.println(calculator.getAcc());
        Command r10 = new Calculator.Repeat(10, add2);
        r10.execute();
        System.out.println(calculator.getAcc());
         */

        CLICalculator c = new CLICalculator(new Calculator());
        c.run();
    }
}
