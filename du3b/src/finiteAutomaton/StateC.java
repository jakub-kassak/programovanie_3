package finiteAutomaton;

class StateC implements AutomatonState {
    private final static StateC instance = new StateC();

    private StateC(){}

    public static StateC create(){
        return instance;
    }

    @Override
    public boolean isAcceptingState() {
        return true;
    }

    @Override
    public AutomatonState transition(char c) {
        if (c == 'a')
            return this;
        if (c == 'b')
            return StateD.create();
        return null;
    }
    // @TODO ...
}

