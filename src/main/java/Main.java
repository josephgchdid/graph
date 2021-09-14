import Graph.*;


public class Main {
    public static void main(String[] args){

        long start = System.currentTimeMillis();

        //create GraphList With `String` as its data type
        GraphList<String> graph = new  GraphList<>();

        //create 7 Nodes
        for(int i = 0; i < 7; i++){
            //add returns an int representing the node's unique id
            graph.add("Node " + i);
        }

        //create an Unweighted Relation object
        Relation unWeightedRelation = new Relation(Direction.NO_DIRECTION);

        //Note: The weighted relation object can have any data type, and it can be different than the graph's data type

        WeightedRelation<Integer> weightedRelation = new   WeightedRelation<>(1, Direction.OUT_GOING);

        graph.addEdge(0, 1, unWeightedRelation);

        unWeightedRelation.direction(Direction.OUT_GOING);

        graph.addEdge(0,2,  unWeightedRelation);

        //change the relation's name
        unWeightedRelation.relationName("Related");

        graph.addEdge(2,3, unWeightedRelation);

        graph.addEdge(4,2, weightedRelation);

        unWeightedRelation = new Relation(Direction.UNI_DIRECTION);

        graph.addEdge(5,6, unWeightedRelation);


        //if youre using a GraphMap, it returns NodeMap object
        NodeList<String> node = graph.getNode(1);

        graph.print();

        System.out.println(node.data());

        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        double seconds = (double) timeElapsed / 1000;
        System.out.println("\n------\nTime elapsed : " + seconds + " seconds");
    }
}
