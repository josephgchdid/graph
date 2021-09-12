import Graph.Direction;
import Graph.GraphList;
import Graph.GraphMap;
import Graph.Relation;

public class Main {
    public static void main(String[] args){

        long start = System.currentTimeMillis();

        GraphList<String> graph = new GraphList<>();

        int iter = 7;

        for(int i = 0; i < iter; i++){
            graph.add("index " + i);
        }

        int len = graph.size();

        Relation<String> relation = null;

        relation = new Relation<>(
                "Some data",
                Direction.LEFT_TO_RIGHT);

        graph.addEdge(0, 1, relation);
        graph.addEdge(1,2, relation);
        graph.addEdge(2,3, relation);
        graph.addEdge(3,4, relation);
        graph.addEdge(4,5, relation);
        graph.addEdge(4, 6, relation);

        graph.print();
      // System.out.println(graph.predecessors(1, true));

      //  System.out.println(graph.predecessors(2, true));


        graph.shortestPath(0, 4);

        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        double seconds = (double) timeElapsed / 1000;
        System.out.println("\n------\nTime elapsed : " + seconds + " seconds");
    }
}
