package workbalance;

public interface WorkUnit {

    int getSalary();

    String repr();

    void addMember(WorkUnit workUnit);

    void addWorkload(int amount);

    int getCurrentWorkload();

    WorkUnit getWorkerWithLowestWorkload();
}
