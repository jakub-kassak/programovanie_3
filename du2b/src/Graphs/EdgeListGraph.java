package Graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EdgeListGraph implements Graph{
    final List<Pair<Integer, Integer>> edges = new ArrayList<>();

    @Override
    public <T> T accept(Visitor<T> v) {
        return v.visitEdgeListGraph(this);
    }

}
