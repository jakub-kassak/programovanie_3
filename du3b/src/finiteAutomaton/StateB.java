package finiteAutomaton;

class StateB implements AutomatonState {
    private final static StateB instance = new StateB();

    private StateB(){}

    public static StateB create(){
        return instance;
    }

    @Override
    public AutomatonState transition(char c) {
        if (c == 'a')
            return StateA.create();
        if (c == 'b')
            return StateC.create();
        return null;
    }
}

