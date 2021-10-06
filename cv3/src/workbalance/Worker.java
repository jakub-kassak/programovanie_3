package workbalance;

public class Worker implements WorkUnit {
    private int salary;
    private String name;
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
    }

    @Override
    public int getCurrentWorkload() {
        return workLoad;
    }

    @Override
    public WorkUnit getWorkerWithLowestWorkload() {
        return this;
    }
}
