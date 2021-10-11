package dnd;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class ICG implements MyRandom{
    private final BigInteger a;
    private final BigInteger c;
    private final BigInteger m;
    private Map<BigInteger, BigInteger> table;
    private BigInteger x;

    public ICG(long a, long c, long m, long seed){
        this.a = BigInteger.valueOf(a);
        this.c =  BigInteger.valueOf(c);
        this.m =  BigInteger.valueOf(m);
        this.x =  BigInteger.valueOf(seed);
    }

    @Override
    public long nextLong() {
        x = a.multiply(pow(x, m.subtract(BigInteger.TWO))).add(c).mod(m);
        return x.longValue();
    }
    private void initTable(BigInteger a){
        table = new HashMap<>();
        table.put(BigInteger.ONE, a);
        table.put(BigInteger.TWO, a.multiply(a));
    }

    private BigInteger pow(BigInteger a, BigInteger n){
        initTable(a);
        return count(a, n);
    }

    private BigInteger count(BigInteger a, BigInteger n){
        if (table.containsKey(n)) {
            return table.get(n);
        }
        BigInteger[] half = n.divideAndRemainder(BigInteger.TWO);
        BigInteger b = count(a, half[0]);
        BigInteger c = count(a, half[0].add(half[1]));
        BigInteger ret = b.multiply(c);
        table.put(n, ret);
        return ret;
    }
}

