package globalTesting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class CompositeUnit implements Unit{
    private final String name;
    private final ArrayList<Unit> units = new ArrayList<>();
    private final Strategy strategy;

    public CompositeUnit(String name) {
        this.name = name;
        this.strategy = new MostNeededByOne();
    }

    public CompositeUnit(String name, Strategy strategy){
        this.name = name;
        this.strategy = strategy;
    }

    @Override
    public String getName() {
        return name;
    }

    private int sumValues(Function<Unit, Integer> mapper){
        return units.stream()
                .map(mapper)
                .reduce(0, Integer::sum);
    }

    @Override
    public int freePositions() {
        return sumValues(Unit::freePositions);
    }

    @Override
    public int totalPositions() {
        return sumValues(Unit::totalPositions);
    }

    @Override
    public int filledTeamsCount() {
        return sumValues(Unit::filledTeamsCount);
    }

    @Override
    public int totalTeamsCount() {
        return sumValues(Unit::totalTeamsCount);
    }

    @Override
    public void addPersonell(int count) {
        strategy.addPersonnel(units, count);
    }

    @Override
    public String accept(Visualisation v) {
        return v.visitCompositeUnit(this);
    }

    public List<Unit> getSubunits(){
        return Collections.unmodifiableList(units);
    }

    public void addSubunit(Unit u){
        units.add(u);
    }
}
