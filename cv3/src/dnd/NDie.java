package dnd;

public interface NDie {

    int nextValue();

    int getSidesCount();

    void setRndStrategy(MyRandom rndStrategy);
}
