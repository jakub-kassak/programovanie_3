package Graphs;

import java.util.List;

public class GraphFactory implements Visitor<Graph>{
    private final List<Pair<Integer, Integer>> edges;

    public GraphFactory(List<Pair<Integer, Integer>> edges) {
        this.edges = edges;
    }

    public static Graph createGraph(Graph g, List<Pair<Integer, Integer>> edges){
        return g.accept(new GraphFactory(edges));
    }

    public static EdgeListGraph createEdgeListGraph(List<Pair<Integer, Integer>> edges){
        EdgeListGraph g = new EdgeListGraph();
        g.edges.addAll(edges);
        return g;
    }

    public static ListGraph createListGraph(List<Pair<Integer, Integer>> edges,  int vertexCount){
        ListGraph g = new ListGraph(vertexCount);
        for (Pair<Integer, Integer> p : edges){
            g.neighbours.get(p.first).add(p.second);
            g.neighbours.get(p.second).add(p.first);
        }
        return g;
    }

    public static TableGraph createTableGraph(List<Pair<Integer, Integer>> edges,  int vertexCount){
        TableGraph g = new TableGraph(vertexCount);
        for (Pair<Integer, Integer> p : edges){
            g.matrix[p.first][p.second] = true;
            g.matrix[p.second][p.first] = true;
        }
        return g;
    }

    @Override
    public Graph visitTableGraph(TableGraph g) {
        for (Pair<Integer, Integer> p : edges){
            g.matrix[p.first][p.second] = true;
            g.matrix[p.second][p.first] = true;
        }
        return g;
    }

    @Override
    public Graph visitListGraph(ListGraph g) {
        for (Pair<Integer, Integer> p : edges){
            g.neighbours.get(p.first).add(p.second);
            g.neighbours.get(p.second).add(p.first);
        }
        return g;
    }

    @Override
    public Graph visitEdgeListGraph(EdgeListGraph g) {
        g.edges.addAll(edges);
        return g;
    }
}
