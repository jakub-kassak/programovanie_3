package Graphs;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EdgeGenerator {

    public static List<Pair<Integer, Integer>> generate(int vertexCount, int edgeCount){
        edgeCount = Math.min(edgeCount, vertexCount*(vertexCount - 1) / 2);
        Random random = new Random();
        List<Integer> vertices = IntStream.range(0, vertexCount).boxed().collect(Collectors.toCollection(ArrayList::new));
        Collections.shuffle(vertices);
        Set<Pair<Integer, Integer>> edges = new HashSet<>();
        for (int i = 0; i < vertexCount - 1; i++) {
            edges.add(new Pair<>(vertices.get(i), vertices.get(i + 1)));
        }

        while (edges.size() < edgeCount) {
            edges.add(new Pair<>(random.nextInt(vertexCount), random.nextInt(vertexCount)));
        }

        return new ArrayList<>(edges);
    }

    public static List<Pair<Integer, Integer>> generateComplete(int vertexCount){
        List<Pair<Integer, Integer>> edges = new ArrayList<>();
        for (int i = 0; i < vertexCount; i++) {
            for (int j = i + 1; j < vertexCount; j++) {
                edges.add(new Pair<>(i, j));
            }
        }
        return edges;
    }
}
