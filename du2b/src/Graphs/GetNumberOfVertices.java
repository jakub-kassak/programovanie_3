package Graphs;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GetNumberOfVertices implements Visitor<Integer>{

    @Override
    public Integer visitTableGraph(TableGraph g) {
        return g.count;
    }

    @Override
    public Integer visitListGraph(ListGraph g) {
        return g.count;
    }

    @Override
    public Integer visitEdgeListGraph(EdgeListGraph g) {
        return g.edges.parallelStream()
                .flatMap(pair -> Stream.of(pair.first, pair.second))
                .collect(Collectors.toSet()).size();
    }
}