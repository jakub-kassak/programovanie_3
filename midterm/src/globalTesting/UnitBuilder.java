package globalTesting;

import java.util.function.Function;

public class UnitBuilder {
    private String name = "";

    Unit addTeams(CompositeUnit u, String name, int count, int capacity){
        for (int i = 0; i < count; i++) {
            u.addSubunit(new TestingTeam(createName(name,i + 1), capacity));
        }
        return u;
    }

    public Unit createSmallTestingSite(String name){
        return addTeams(new CompositeUnit(name), name, 2, 2);
    }

    public Unit createMediumTestingSite(String name){
        return addTeams(new CompositeUnit(name), name,4, 3);
    }

    public Unit createLargeTestingSite(String name){
        return addTeams(new CompositeUnit(name), name,8, 4);
    }

    private String createName(String name, int i){
        return "%s_%d".formatted(name, i);
    }


    public Unit createStructureForSettlement(String name, int population){
        this.name = name;
        CompositeUnit u = new CompositeUnit(name, new MostCompleteByOne());
        int i = 0;
        if (population > 100_000){
            int pop2 = population;
            while (pop2 > 0){
                u.addSubunit(createLargeTestingSite(createName(name, i++)));
                pop2 -= 100_000;
            }
        }
        if (population > 10_000) {
            int pop2 = population;
            while (pop2 > 0) {
                u.addSubunit(createMediumTestingSite(createName(name, i++)));
                pop2 -= 10_000;
            }
        }
        while (population > 0){
            u.addSubunit(createSmallTestingSite(createName(name, i++)));
            population -= 1000;
        }
        return u;
    }
}
