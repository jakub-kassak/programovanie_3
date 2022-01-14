package Graphs;

import java.util.ArrayList;
import java.util.List;

public class TimeTracker implements Graph{
    private final Graph g;
    private final List<Pair<String, Long>> timestamps = new ArrayList<>();
    private long time = 0;

    public TimeTracker(Graph g) {
        this.g = g;

    }

    public void addTimestamp(String label){
        timestamps.add(new Pair<>(label, getTotalTime()));
    }

    public long getTotalTime(){
        return time;
    }

    public List<Pair<String, Long>> getTimestamps(){
        return timestamps;
    }

    @Override
    public <T> T accept(Visitor<T> v) {
        long start = System.nanoTime();
        T ret = g.accept(v);
        long end = System.nanoTime();
        time += end - start;
        return ret;
    }
}
