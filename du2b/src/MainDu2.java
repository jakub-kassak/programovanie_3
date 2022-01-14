import Graphs.*;

import java.util.*;
import java.util.function.Supplier;

public class MainDu2 {
    private static final String ASCII_GOOD = "\u001b[32m";
    private static final String ASCII_BAD = "\u001b[31m";
    private static final String ASCII_RESET = "\u001b[0m";

//    public static void main(String[] args) {
//        LinkedList<Integer> list = new LinkedList<>();
//        list.add(1);
//        list.add(2);
//        list.addAll(List.of(3, 4));
//        System.out.println(list);
//        System.out.println(list.remove());
//        System.out.println(list);
//        list.add(5);
//        System.out.println(list.remove());
//        System.out.println(list.remove());
//        System.out.println(list.remove());
//        System.out.println(list);
//
//    }
    public static void main(String[] args) {
        checkAssert();
        checkPart(MainDu2::checkGetNeighbours, "checkGetNeighbours");
        checkPart(MainDu2::checkGetNumberOfVertices, "checkGetNumberOfVertices");
        checkPart(MainDu2::checkGraphDiameter, "checkGraphDiameter");
    }

    private static void assertGetNeighbours(List<Pair<Integer, Integer>> edges, int vertexCount, Set<Integer> neighbours, int vertex){
        EdgeListGraph ge = GraphFactory.createEdgeListGraph(edges);
        TableGraph gt = GraphFactory.createTableGraph(edges, vertexCount);
        ListGraph gl = GraphFactory.createListGraph(edges, vertexCount);

        assertEqual(neighbours, ge.accept(new GetNeighbours(vertex)));
        assertEqual(neighbours, gl.accept(new GetNeighbours(vertex)));
        assertEqual(neighbours, gt.accept(new GetNeighbours(vertex)));


    }

    private static Void checkGetNeighbours(){
        List<Pair<Integer, Integer>> edges1 = List.of(new Pair<>(1, 2), new Pair<>(2, 3), new Pair<>(3, 0));
        Set<Integer> neighbours1_1 = Set.of(2);
        assertGetNeighbours(edges1, 4, neighbours1_1, 1);
        Set<Integer> neighbours1_2 = Set.of(1, 3);
        assertGetNeighbours(edges1, 4, neighbours1_2, 2);
        List<Pair<Integer, Integer>> edges2 = EdgeGenerator.generateComplete(10);
        Set<Integer> neighbours2_5 = Set.of(0, 1, 2, 3, 4, 6, 7, 8, 9);
        assertGetNeighbours(edges2, 10, neighbours2_5, 5);

        int vertexCount = 100;
        List<Pair<Integer, Integer>> edges3 = EdgeGenerator.generate(vertexCount, 500);
        Graph gt = GraphFactory.createTableGraph(edges3, vertexCount);
        for (int i = 0; i < vertexCount; i++) {
            Set<Integer> neighbours3 = gt.accept(new GetNeighbours(i));
            assertGetNeighbours(edges3, vertexCount, neighbours3, i);
        }

        return null;
    }

    private static void assertGetNumberOfVertices(int vertexCount){
        List<Pair<Integer, Integer>> edges = EdgeGenerator.generate(vertexCount, vertexCount * (vertexCount / 2 + 1));
        EdgeListGraph g = GraphFactory.createEdgeListGraph(edges);
        assertEqual(vertexCount, g.accept(new GetNumberOfVertices()));
    }

    private static Void checkGetNumberOfVertices(){
        List<Pair<Integer, Integer>> edges1 = List.of(new Pair<>(1, 2), new Pair<>(2, 3), new Pair<>(3, 0));
        EdgeListGraph g1 = GraphFactory.createEdgeListGraph(edges1);
        assertEqual(4, g1.accept(new GetNumberOfVertices()));
        for (int i = 2; i < 100; i++) {
            assertGetNumberOfVertices(i);
        }
        return null;
    }

    private static List<Pair<Integer, Integer>> createEdges(int...a){
        List<Pair<Integer, Integer>> edges = new ArrayList<>();
        for (int i = 0; i + 1 < a.length; i+=2) {
            edges.add(new Pair<>(a[i], a[i+1]));
        }
        return edges;
    }

    private static Void checkGraphDiameter(){
        Graph g = GraphFactory.createListGraph(createEdges(1,2, 2,3, 3,0), 4);
        assertEqual(3, GraphDiameter.eval(g));

        g = GraphFactory.createListGraph(createEdges(1,2, 2,3, 3,0, 0, 1), 4);
        assertEqual(2, GraphDiameter.eval(g));

        g = GraphFactory.createListGraph(createEdges(0,1, 0,4, 1,2, 1,3, 2,4, 3,4, 3,5, 4,5), 6);
        assertEqual(2, GraphDiameter.eval(g));

        g = GraphFactory.createListGraph(createEdges(0,1, 1,2, 1,3, 2,4, 3,4, 3,5, 4,5), 6);
        assertEqual(3, GraphDiameter.eval(g));

        g = GraphFactory.createEdgeListGraph(EdgeGenerator.generateComplete(123));
        assertEqual(1, GraphDiameter.eval(g));

        int vertexCount = 24;
        List<Pair<Integer, Integer>> edges = EdgeGenerator.generate(vertexCount, 140);
        Graph gt = GraphFactory.createTableGraph(edges, vertexCount);
        Graph gl = GraphFactory.createListGraph(edges, vertexCount);
        Graph ge = GraphFactory.createEdgeListGraph(edges);

        assertEqual(gt, gl);
        assertEqual(gt, ge);

        assertEqual(GraphDiameter.eval(gt), GraphDiameter.eval(gl));
        assertEqual(GraphDiameter.eval(gt), GraphDiameter.eval(ge));
        return null;
    }



    private static void checkAssert() {
        try {
            assert false;
            System.out.println(ASCII_BAD + "Asserts are ignored, add `-ea` to VM options." + ASCII_RESET);
            System.exit(1);
        }
        catch (Error e){
            System.out.println(ASCII_GOOD + "Asserts are active." + ASCII_RESET);
        }
    }


    private static void checkPart(Supplier<Void> test, String name) {
        try {
            test.get();
            System.out.println(ASCII_GOOD + name + " SUCCESS" + ASCII_RESET);
        }
        catch (Exception | Error e) {
            e.printStackTrace();
            System.out.println(ASCII_BAD + name + " FAIL" + ASCII_RESET);
        }
    }

    private static void assertEqual(Graph g1, Graph g2){
        assert Graph.equalGraphs(g1, g2) : "g1 != g2";
    }



    private static void assertEqual(Set<Integer> a, Set<Integer> b){
        assert a.equals(b) : "'" + a + "' != '" + b + "'";
    }

    private static void assertEqual(double a, double b) {
        final double eps = 1e-9;
        assert Math.abs(a - b) < eps : a + " != " + b;
    }

    private static void assertEqual(int a, int b) {
        assert a == b : a + " != " + b;
    }

    private static void assertEqual(String a, String b) {
        assert a.equals(b) : "'"+ a + "' != '" + b + "'";
    }

}
