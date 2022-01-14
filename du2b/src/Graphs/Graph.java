package Graphs;

public interface Graph {
    <T> T accept(Visitor<T> v);

    static boolean equalGraphs(Graph g1, Graph g2){
        int vertexCount = g1.accept(new GetNumberOfVertices());
        if (vertexCount != g2.accept(new GetNumberOfVertices()))
            return false;
        ExistsEdge existsEdge = new ExistsEdge();
        for (int i = 0; i < vertexCount; i++) {
            for (int j = i; j < vertexCount; j++) {
                existsEdge.set(i, j);
                if (g1.accept(existsEdge) != g2.accept(existsEdge))
                    return false;
            }
        }
        return true;
    }
}
