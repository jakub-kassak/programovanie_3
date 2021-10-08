package workbalance;

import java.util.LinkedList;
import java.util.Scanner;

public class Team extends AbstractWorkUnit implements WorkUnit {
    private final LinkedList<WorkUnit> teamMembers = new LinkedList<>();
    private final String name;
    private WorkUnit workerWithLowestWorkload = null;

    public Team(String name){
        this.name = name;
    }

    @Override
    public void addMember(WorkUnit w){
        teamMembers.add(w);
        if (workerWithLowestWorkload == null)
            workerWithLowestWorkload = w;
        else
            updateWorkerWithLowestWorkload();
    }

    @Override
    public void addWorkload(int amount) {
        WorkUnit workUnit = getWorkerWithLowestWorkload();
        workUnit.addWorkload(amount);
    }

    @Override
    public int getCurrentWorkload() {
        return teamMembers
                .stream()
                .mapToInt(WorkUnit::getCurrentWorkload)
                .sum();
    }

    @Override
    public WorkUnit getWorkerWithLowestWorkload() {
        return workerWithLowestWorkload;
    }

    public void updateWorkerWithLowestWorkload() {
        int minLoad = Integer.MAX_VALUE;
        for (WorkUnit member : teamMembers){
            WorkUnit w = member.getWorkerWithLowestWorkload();
            if (w.getCurrentWorkload() < minLoad){
                minLoad = w.getCurrentWorkload();
                workerWithLowestWorkload = w;
            }
        }
        if (parent != null)
            parent.updateWorkerWithLowestWorkload();
    }

    @Override
    public int getSalary() {
        return teamMembers
                .stream()
                .mapToInt(WorkUnit::getSalary)
                .sum();
    }

    @Override
    public String repr(){
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append(" (salary: ");
        sb.append(getSalary());
        sb.append(", workload: ");
        sb.append(getCurrentWorkload());
        sb.append(")");
        for (WorkUnit w: teamMembers){
            Scanner sc = new Scanner(w.repr());
            while (sc.hasNext()){
                sb.append("\n");
                sb.append(" ".repeat(6));
                sb.append(sc.nextLine());
            }
        }
        return sb.toString();
    }
}
