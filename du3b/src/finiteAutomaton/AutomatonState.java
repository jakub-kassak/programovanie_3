package finiteAutomaton;

interface AutomatonState {
    default boolean isAcceptingState() {
        return false;
    }
    AutomatonState transition(char c);
}
