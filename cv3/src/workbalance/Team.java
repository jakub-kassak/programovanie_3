package workbalance;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Team implements WorkUnit {
    private final LinkedList<WorkUnit> teamMebers = new LinkedList<>();
    private final String name;
    private WorkUnit workerWithLowestWorkload = null;

    public Team(String name, List<WorkUnit> teamMembers){
        this.name = name;
        for(WorkUnit wu : teamMembers){
            this.teamMebers.add(wu);
        }
    }

    public Team(String name){
        this.name = name;
    }

    @Override
    public void addMember(WorkUnit w){
        teamMebers.add(w);
    }

    @Override
    public void addWorkload(int amount) {
        WorkUnit workUnit = getWorkerWithLowestWorkload();
        workUnit.addWorkload(amount);
    }

    @Override
    public int getCurrentWorkload() {
        return teamMebers
                .stream()
                .mapToInt(WorkUnit::getCurrentWorkload)
                .sum();
    }

    @Override
    public WorkUnit getWorkerWithLowestWorkload() {
        if (workerWithLowestWorkload != null)
            return workerWithLowestWorkload;

        int minLoad = Integer.MAX_VALUE;
        WorkUnit leastLoaded = null;
        for (WorkUnit member : teamMebers){
            WorkUnit w = member.getWorkerWithLowestWorkload();
            if (w.getCurrentWorkload() < minLoad){
                minLoad = w.getCurrentWorkload();
                leastLoaded = w;
            }
        }
        return leastLoaded;
    }

    @Override
    public int getSalary() {
        return teamMebers
                .stream()
                .mapToInt(WorkUnit::getSalary)
                .sum();
    }

    @Override
    public String repr() {
        return repr(4);
    }

    private String repr(int offset){
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append(" (total salary: ");
        sb.append(getSalary());
        sb.append(", total workload: ");
        sb.append(getCurrentWorkload());
        sb.append(")");
        for (WorkUnit w: teamMebers){
            Scanner sc = new Scanner(w.repr());
            while (sc.hasNext()){
                sb.append("\n");
                for (int i = 0; i < offset; i++) {
                    sb.append(' ');
                }
                sb.append(sc.nextLine());
            }
        }
        return sb.toString();
    }
}
