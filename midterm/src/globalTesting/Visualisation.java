package globalTesting;

import java.util.Scanner;

public class Visualisation {
    StringBuilder sb = new StringBuilder();
    int tabsCount = 0;

    String visitTestingTeam(TestingTeam team){
        sb.append("  ".repeat(tabsCount));
        sb.append("%s: %d/%d filled".formatted(team.getName(), team.totalPositions() - team.freePositions(), team.totalPositions()));
        return sb.toString();
    }

    String visitCompositeUnit(CompositeUnit u){
        sb.append("  ".repeat(tabsCount));
        sb.append("%s: %d/%d filled positions, %d/%d filled teams".formatted(u.getName(), u.totalPositions() - u.freePositions(), u.totalPositions(), u.filledTeamsCount(), u.totalTeamsCount()));
        tabsCount++;
        u.getSubunits().forEach(x -> {
            sb.append("\n");
            x.accept(this);
        });
        tabsCount--;
        return sb.toString();
    }
}
