package workbalance;

public class Worker extends AbstractWorkUnit implements WorkUnit {
    private final int salary;
    private final String name;
    private int workLoad = 0;

    public Worker(String name, int salary){
        this.name = name;
        this.salary = salary;
    }

    @Override
    public int getSalary() {
        return salary;
    }

    @Override
    public String repr() {
        return name + " (salary: " + salary + ", workload: " + workLoad + ")";
    }

    @Override
    public void addMember(WorkUnit workUnit) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addWorkload(int amount) {
        workLoad += amount;
        if (parent != null)
            parent.updateWorkerWithLowestWorkload();
    }

    @Override
    public int getCurrentWorkload() {
        return workLoad;
    }

    @Override
    public void updateWorkerWithLowestWorkload() {

    }

    @Override
    public WorkUnit getWorkerWithLowestWorkload() {
        return this;
    }
}
