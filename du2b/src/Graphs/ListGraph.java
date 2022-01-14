package Graphs;

import java.util.ArrayList;
import java.util.List;

public class ListGraph implements Graph{
    final List<List<Integer>> neighbours = new ArrayList<>();
    final int count;

    public ListGraph(int count){
        this.count = count;
        for (int i = 0; i < count; i++) {
            neighbours.add(new ArrayList<>());
        }
    }

    @Override
    public <T> T accept(Visitor<T> v) {
        return v.visitListGraph(this);
    }
}
