package dnd;

public class LCG implements MyRandom{
    private final long a;
    private final long c;
    private final long m;
    private long x;

    public LCG(long a, long c, long m, long seed){
        this.a = a;
        this.c = c;
        this.m = m;
        this.x = seed;
    }

    @Override
    public long nextLong() {
        x = (a * x + c) % m;
        return x;
    }
}
