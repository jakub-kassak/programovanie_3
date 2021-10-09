package dnd;


public class Dn implements NDie{
    private final int sidesCount;

    private MyRandom rndStrategy ;

    public Dn(int sidesCount){
        this.rndStrategy = new StandardRandom();
        this.sidesCount = sidesCount;
    }

    public void setRndStrategy(MyRandom rndStrategy){
        this.rndStrategy = rndStrategy;
    }

    public int nextValue() {
        return (int) ((rndStrategy.nextLong() & Long.MAX_VALUE) % sidesCount);
    }

    public int getSidesCount(){
        return sidesCount;
    }

}
