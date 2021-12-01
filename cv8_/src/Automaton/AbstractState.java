package Automaton;

public class AbstractState implements State{
    final char a;
    final char b;
    private final boolean accepting;
    private boolean finished;

    public AbstractState(char a, char b, boolean accepting) {
        this.a = a;
        this.b = b;
        this.accepting = accepting;
    }

    private State createNewState(char c){
        switch (c){
            case 'a' -> {return new A();}
            case 'b' -> {return new B();}
            case 'c' -> {return new C();}
            case 'd' -> {return new D();}
            default -> {return null;}
        }
    }

    @Override
    public void read(DFA dfa) {
        finished = true;
        if (dfa.word.length() > 0){
            if (dfa.word.charAt(0) == 'a')
                dfa.state = createNewState(a);
            if (dfa.word.charAt(0) == 'b')
                dfa.state = createNewState(b);
            dfa.word = dfa.word.substring(1);
        }
    }

    @Override
    public boolean isAccepting() {
        return accepting;
    }

    @Override
    public boolean isFinished() {
        return finished;
    }
}
