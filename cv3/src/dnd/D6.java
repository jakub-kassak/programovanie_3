package dnd;


public class D6 implements NDie{

    private MyRandom rndStrategy ;

    public D6(){
        this.rndStrategy = new StandardRandom();
    }

    public void setRndStrategy(MyRandom rndStrategy){
        this.rndStrategy = rndStrategy;
    }

    public int nextValue() {
        return (int) ((rndStrategy.nextLong() & Long.MAX_VALUE) % 6);
    }

    public int getSidesCount(){
        return 6;
    }

}
