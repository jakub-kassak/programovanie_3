package globalTesting;

public class TestingTeam implements Unit{
    private final int total;
    private int free;
    private final String name;

    public TestingTeam(String name, int total) {
        this.total = total;
        this.name = name;
        free = total;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int freePositions() {
        return free;
    }

    @Override
    public int totalPositions() {
        return total;
    }

    @Override
    public int filledTeamsCount() {
        if (free == 0)
            return 1;
        return 0;
    }

    @Override
    public int totalTeamsCount() {
        return 1;
    }

    @Override
    public void addPersonell(int count) {
        free -= count;
    }

    @Override
    public String accept(Visualisation v) {
        return v.visitTestingTeam(this);
    }
}
