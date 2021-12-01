import Automaton.DFA;

public class AutomatonDemo {
    public static void main(String[] args) {
        DFA a = new DFA("abbbbbb");
        while (!a.isFinished()){
            a.read();
        }
        System.out.println(a.isAccepting());
    }
}
