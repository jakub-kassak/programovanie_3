package Automaton;

public class DFA {
    State state = new A();
    String word;

    public DFA(String w){
        this.word = w;
    }

    public boolean isFinished(){
        return state.isFinished();
    }

    public boolean isAccepting(){
        return state.isFinished() && state.isAccepting();
    }

    public void read(){
        state.read(this);
    }
}
