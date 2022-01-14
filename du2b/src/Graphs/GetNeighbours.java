package Graphs;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class GetNeighbours implements Visitor<Set<Integer>> {
    int vertex;

    public GetNeighbours(int vertex) {
        this.vertex = vertex;
    }

    public GetNeighbours(){}

    @Override
    public Set<Integer> visitTableGraph(TableGraph g) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < g.count; i++) {
            if (g.matrix[vertex][i])
                set.add(i);
        }
        return set;
    }

    @Override
    public Set<Integer> visitListGraph(ListGraph g) {
        return new HashSet<>(g.neighbours.get(vertex));
    }

    @Override
    public Set<Integer> visitEdgeListGraph(EdgeListGraph g) {

        return g.edges.stream()
                .map(x -> {
                    if (x.first == vertex)
                        return x.second;
                    if (x.second == vertex)
                        return x.first;
                    return -1;
                })
                .filter(x -> x != -1)
                .collect(Collectors.toSet());
    }
}
