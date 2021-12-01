package Automaton;

public interface State {
    void read(DFA dfa);
    boolean isAccepting();
    boolean isFinished();
}
