package Graphs;

public class ExistsEdge implements Visitor<Boolean> {
    private int from;
    private int to;

    ExistsEdge set(int from, int to){
        this.from = from;
        this.to = to;
        return this;
    }

    @Override
    public Boolean visitTableGraph(TableGraph g) {
        return g.matrix[from][to];
    }

    @Override
    public Boolean visitListGraph(ListGraph g) {
        return g.neighbours.get(from).contains(to);
    }

    @Override
    public Boolean visitEdgeListGraph(EdgeListGraph g) {
        return g.edges.contains(new Pair<>(from, to))
                || g.edges.contains(new Pair<>(to, from));
    }
}
