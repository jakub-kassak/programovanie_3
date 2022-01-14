package Graphs;

public interface Visitor<T> {
    T visitTableGraph(TableGraph g);
    T visitListGraph(ListGraph g);
    T visitEdgeListGraph(EdgeListGraph g);
}
