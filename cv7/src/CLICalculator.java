import Calculator.*;

import java.util.ArrayList;
import java.util.Scanner;

public class CLICalculator {
    private final Calculator calc;
    private final ArrayList<CalcOp> history = new ArrayList<>();
    private Scanner sc;
    private int index = -1;

    public CLICalculator(Calculator calc) {
        this.calc = calc;
    }

    private void handleOp(String op){
        while (index < history.size() - 1)
            history.remove( history.size() - 1);
        int value = sc.nextInt();
        switch (op.charAt(0)) {
            case '+' -> history.add(new Add(calc, value));
            case '-' -> history.add(new Add(calc, -value));
            case '*' -> history.add(new Multiply(calc, value));
            case 'R' -> history.add(new Repeat(calc, history.get(history.size() - 1), value));
        }
        history.get(++index).execute();
    }

    public void run() {
        System.out.println(calc.getAcc());
        sc = new Scanner(System.in);

        label: while (sc.hasNext()){
            String op = sc.next();
            switch (op) {
                case "EXIT" -> {break label;}
                case "U" -> {
                    if (history.size() > 0 && index >= 0) {
                        history.get(index).undo();
                        index--;
                    } else
                        System.out.println("No more undo left");
                }
                case "Y" -> {
                    if (index < history.size() - 1) {
                        history.get(++index).execute();
                    } else
                        System.out.println("No more redo left");
                }
                case "+", "-", "*", "R" -> handleOp(op);
                default -> System.out.println("Unknown operation: " + op);
            }
            System.out.println(calc.getAcc());
        }
    }
}
