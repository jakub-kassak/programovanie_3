package dnd;


public class D4 implements NDie{

    private MyRandom rndStrategy ;

    public D4(){
        this.rndStrategy = new StandardRandom();
    }

    public void setRndStrategy(MyRandom rndStrategy){
        this.rndStrategy = rndStrategy;
    }

    public int nextValue() {
        return (int) ((rndStrategy.nextLong() & Long.MAX_VALUE) % 4);
    }

    public int getSidesCount(){
        return 4;
    }

}
