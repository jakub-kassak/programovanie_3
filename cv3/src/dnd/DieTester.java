package dnd;

import java.util.*;

import static java.lang.Math.log;

public class DieTester {

    public static void testDistribution(NDie die, int tries) {
        int n = die.getSidesCount();
        Map<Integer, Integer> distribution = new HashMap<>();
        for (int i = 0; i < n; i++) {
            distribution.put(i, 0);
        }
        for (int i = 0; i < tries; i++) {
            int value = die.nextValue();
            distribution.put(value, distribution.get(value) + 1);
        }
        System.out.println(distribution);
        System.out.println(evalEntropy(distribution.values(), tries));
    }


    public static void testConsecutivePairs(NDie die, int tries) {
        int n = die.getSidesCount();
        Map<String, Integer> distribution = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                String key = i + "x" + j;
                distribution.put(key, 0);
            }
        }

        int prevVal = die.nextValue();
        for (int i = 0; i < tries; i++) {
            int nextValue = die.nextValue();
            String key = prevVal + "x" + nextValue;
            distribution.put(key, distribution.get(key) + 1);
            prevVal = nextValue;
        }
        System.out.println(distribution);
        System.out.println(evalEntropy(distribution.values(), tries));
    }

    private static double evalEntropy(Collection<Integer> distribution, int n){
        double sum = 0;
        for (Integer value : distribution){
            if (value != 0) {
                double p = value / (double) n;
                sum -= p * log(p) / log(2);
            }
        }
        return sum;
    }
}
