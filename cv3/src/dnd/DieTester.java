package dnd;

import java.util.HashMap;
import java.util.Map;

public class DieTester {

    static void testDistribution(NDie die, int tries) {
        Map<Integer, Integer> distribution = new HashMap<>();
        for (int i = 0; i < tries; i++) {
            int value = die.nextValue();
            distribution.put(value, distribution.getOrDefault(value, 0) + 1);
        }
        System.out.println(distribution);
    }
}
