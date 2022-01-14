package globalTesting;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToIntFunction;

public class MostCompleteByOne implements Strategy{
    @Override
    public void addPersonnel(List<Unit> units, int count) {
        for (int i = 0; i < count; i++) {
            units = units.stream().filter(x -> x.freePositions() > 0).toList();
            ToIntFunction<Unit> unitComparator = x -> x.totalTeamsCount() - x.filledTeamsCount();
            Collections.min(units, Comparator.comparingInt(unitComparator))
                    .addPersonell(1);
        }

    }
}
