import Graphs.*;

import java.util.List;

public class Experiments {

    static class GraphType implements Visitor<String>{

        @Override
        public String visitTableGraph(TableGraph g) {
            return "TableGraph";
        }

        @Override
        public String visitListGraph(ListGraph g) {
            return "ListGraph";
        }

        @Override
        public String visitEdgeListGraph(EdgeListGraph g) {
            return "EdgeListGraph";
        }
    }

    public static void main(String[] args) {
        int[] vertexCounts = {100, 100, 100, 200, 200, 200, 500, 500, 500, 1000};
        int[] edgeCounts = {500, 1000, 3000, 500, 1000, 3000, 500, 1000, 3000, 3000};
        System.out.printf("%4s | %4s | %14s | %8s | %18s | %18s%n", "N", "K", "Type", "Init", "Diameter", "Total");
        System.out.println("-".repeat(81));
        for (int i = 0; i < vertexCounts.length; i++) {
            List<Pair<Integer, Integer>> edges = EdgeGenerator.generate(vertexCounts[i], edgeCounts[i]);
            List<TimeTracker> graphs = List.of(
                    new TimeTracker(new TableGraph(vertexCounts[i])),
                    new TimeTracker(new ListGraph(vertexCounts[i])),
                    new TimeTracker(new EdgeListGraph())
            );
            for (TimeTracker g : graphs){
                GraphFactory.createGraph(g, edges);
                g.addTimestamp("init");
                GraphDiameter.eval(g);
                g.addTimestamp("diameter");
                List<Pair<String, Long>> timestamps = g.getTimestamps();
                System.out.printf("%4d | %4d | %14s | %8d | %18d | %18d%n",
                        vertexCounts[i],
                        edgeCounts[i],
                        g.accept(new GraphType()),
                        timestamps.get(0).second,
                        timestamps.get(1).second - timestamps.get(0).second,
                        timestamps.get(1).second);
            }
//            graphs.forEach(g -> GraphFactory.createGraph(g, edges));
//            graphs.forEach(g -> g.addTimestamp("init"));
//            graphs.forEach(GraphDiameter::eval);
//            graphs.forEach(g -> g.addTimestamp("diameter"));
//            int finalI = i;
//            graphs.stream()
//                    .map(Experiments::timeTrackerToStr)
//                    .map(s -> "%4d| %d\t| %s".formatted(vertexCounts[finalI], edgeCounts[finalI], s))
//                    .forEach(System.out::println);

        }
    }
}
//
//             N |    K |           Type |     Init |           Diameter |              Total
//           --------------------------------------------------------------------------------
//            100 |  500 |     TableGraph |   195400 |           36212800 |           36408200
//            100 |  500 |      ListGraph |   165400 |           26767300 |           26932700
//            100 |  500 |  EdgeListGraph |    48000 |          524992000 |          525040000
//            100 | 1000 |     TableGraph |   180000 |           14363300 |           14543300
//            100 | 1000 |      ListGraph |   377900 |           10055100 |           10433000
//            100 | 1000 |  EdgeListGraph |    41600 |          353484200 |          353525800
//            100 | 3000 |     TableGraph |   374300 |            9035100 |            9409400
//            100 | 3000 |      ListGraph |   639000 |            7135600 |            7774600
//            100 | 3000 |  EdgeListGraph |    39200 |          695761600 |          695800800
//            200 |  500 |     TableGraph |    62600 |          262049500 |          262112100
//            200 |  500 |      ListGraph |   100200 |           77951500 |           78051700
//            200 |  500 |  EdgeListGraph |     7400 |         2116071500 |         2116078900
//            200 | 1000 |     TableGraph |   177800 |          206573900 |          206751700
//            200 | 1000 |      ListGraph |   187300 |           71855600 |           72042900
//            200 | 1000 |  EdgeListGraph |     6900 |         2584331300 |         2584338200
//            200 | 3000 |     TableGraph |   370900 |          104910500 |          105281400
//            200 | 3000 |      ListGraph |   581200 |           56212900 |           56794100
//            200 | 3000 |  EdgeListGraph |     9900 |         4546776000 |         4546785900
//            500 |  500 |     TableGraph |    59400 |        19024863000 |        19024922400
//            500 |  500 |      ListGraph |   101900 |         1838847900 |         1838949800
//            500 |  500 |  EdgeListGraph |    11500 |        64128366000 |        64128377500
//            500 | 1000 |     TableGraph |   123700 |        10636569000 |        10636692700
//            500 | 1000 |      ListGraph |   285800 |         1504797800 |         1505083600
//            500 | 1000 |  EdgeListGraph |     7900 |        67175538000 |        67175545900
//            500 | 3000 |     TableGraph |   361900 |         4583572400 |         4583934300
//            500 | 3000 |      ListGraph |   552000 |         1161606500 |         1162158500
//            500 | 3000 |  EdgeListGraph |    12700 |        82192344300 |        82192357000
//           1000 | 3000 |     TableGraph |   422500 |       105769485000 |       105769907500
//           1000 | 3000 |      ListGraph |   586500 |        11841085200 |        11841671700
