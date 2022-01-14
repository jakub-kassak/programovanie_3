package finiteAutomaton;

class StateA implements AutomatonState {
    private final static StateA instance = new StateA();

    private StateA(){}

    public static StateA create(){
        return instance;
    }

    @Override
    public AutomatonState transition(char c) {
        if (c == 'a')
            return StateB.create();
        if (c == 'b')
            return this;
        return null;
    }
}
