package Graphs;

import java.util.*;
import java.util.stream.Collectors;

public class GraphDiameter {

    static public int eval(Graph g){
        int radius = Integer.MIN_VALUE;
        int vertexCount = g.accept(new GetNumberOfVertices());
        for (int i = 0; i < vertexCount; i++) {
            for (int j = i + 1; j < vertexCount; j++) {
                int distance = search(g, i, j);
                radius = Math.max(radius, distance);
            }
        }
        return radius;
    }

    private static int search(Graph g, int from, int to){
        int[] visited = new int[g.accept(new GetNumberOfVertices())];
        Arrays.fill(visited, -1);
        GetNeighbours getNeighbours = new GetNeighbours();
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(from);
        visited[from] = 0;


        while (queue.size() > 0){
            int vertex = queue.remove();
            getNeighbours.vertex = vertex;
            Set<Integer> neighbours = g.accept(getNeighbours).stream()
                    .filter(x -> visited[x] == -1)
                    .collect(Collectors.toSet());
            queue.addAll(neighbours);
            for (Integer w : neighbours)
                visited[w] = visited[vertex] + 1;
            if (visited[to] >= 0)
                return visited[to];
        }
        return Integer.MAX_VALUE;
    }

    public static void main(String[] args) {
        int vertexCount = 24;
        List<Pair<Integer, Integer>> edges = EdgeGenerator.generate(vertexCount, 140);
        Graph gt = GraphFactory.createTableGraph(edges, vertexCount);
        Graph gl = GraphFactory.createListGraph(edges, vertexCount);
        Graph ge = GraphFactory.createEdgeListGraph(edges);

        int i;
        int j = 0;
        label: for (i = 0; i < vertexCount; i++) {
            for (j = i; j < vertexCount; j++) {
                if (search(gt, i, j) != search(gl, i, j))
                    break label;
            }
        }
        while (true){
            Scanner sc = new Scanner(System.in);
            System.out.println(search(gt, i, j));
            System.out.println(search(gl, i, j));
            String next = sc.next();
        }
    }
}
