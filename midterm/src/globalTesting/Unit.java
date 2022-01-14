package globalTesting;

public interface Unit {
    String getName();
    int freePositions();
    int totalPositions();
    int filledTeamsCount();
    int totalTeamsCount();
    void addPersonell(int count);
    String accept(Visualisation v);

}
