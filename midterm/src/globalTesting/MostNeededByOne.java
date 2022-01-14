package globalTesting;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MostNeededByOne implements Strategy{
    @Override
    public void addPersonnel(List<Unit> units, int count) {
        for (int i = 0; i < count; i++) {
            Unit u = Collections.max(units, Comparator.comparingInt(Unit::freePositions));
            u.addPersonell(1);
        }
    }
}
