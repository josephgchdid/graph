import Graph.Direction;
import Graph.GraphList;
import Graph.Relation;
import Graph.WeightedRelation;


public class Main {
    public static void main(String[] args){

        long start = System.currentTimeMillis();

        GraphList<String> graph = new GraphList<>();

        int iter = 7;

        for(int i = 0; i < iter; i++){
            graph.add("index " + i);
        }

        int len = graph.size();


        Relation weightedRelation = null;

        weightedRelation = new Relation(Direction.OUT_GOING);

        graph.addEdge(0, 1, weightedRelation);
        graph.addEdge(1,2, weightedRelation);
        graph.addEdge(2,3, weightedRelation);
        graph.addEdge(3,4, weightedRelation);
        graph.addEdge(4,5, weightedRelation);
        graph.addEdge(4, 6, weightedRelation);

        graph.print();

        System.out.println(graph.getNode(1).relations(0));
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        double seconds = (double) timeElapsed / 1000;
        System.out.println("\n------\nTime elapsed : " + seconds + " seconds");
    }
}
