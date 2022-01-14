package finiteAutomaton;

public class FiniteAutomaton {
    private AutomatonState state = StateA.create();

    public boolean isAcceptingState() {
        return state.isAcceptingState();
    }

    public void transition(char c) {
        state = state.transition(c);
    }
}
