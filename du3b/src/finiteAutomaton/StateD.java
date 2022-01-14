package finiteAutomaton;

class StateD implements AutomatonState {
    private final static StateD instance = new StateD();

    private StateD(){}

    public static StateD create(){
        return instance;
    }

    @Override
    public AutomatonState transition(char c) {
        if (c == 'a')
            return StateC.create();
        if (c == 'b')
            return this;
        return null;
    }
}

