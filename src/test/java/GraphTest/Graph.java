package GraphTest;

import Graph.Direction;
import Graph.GraphList;
import Graph.WeightedRelation;
import org.junit.Test;

import static org.junit.Assert.*;

public class Graph {

    @Test
    public void countNodesAndRelations(){

        GraphList<Integer> graph = new GraphList<>();

        int len = 5;

        for(int i = 0 ; i < len; i++ ){
            graph.add(i);
        }

        assertEquals("Unexpected graph size",graph.size(), 5);

        WeightedRelation<Integer> weightedRelation = new WeightedRelation<>(Direction.NO_DIRECTION);

        graph.addEdge(0,1, weightedRelation);
        graph.addEdge(1,2, weightedRelation);
        graph.addEdge(2,3, weightedRelation);
        graph.addEdge(3,4, weightedRelation);

        assertEquals("failure - graph relations are not equal",graph.relations(), 4);
    }

    @Test
    public void removeRelations(){

        GraphList<Integer> graph = new GraphList<>();

        int len = 5;

        for(int i = 0 ; i < len; i++ ){
            graph.add(i);
        }

        WeightedRelation<Integer> weightedRelation = new WeightedRelation<>(Direction.NO_DIRECTION);

        graph.addEdge(0,1, weightedRelation);

        graph.addEdge(1,2, weightedRelation);

        graph.removeEdge(0,1);

        graph.removeEdge(1, 2);

        assertFalse("Failure - 0 - 1 should have no edge", graph.hasEdge(0 , 1));

        assertFalse("Failure - 1 - 2 should have no edge", graph.hasEdge(1 , 2));

        assertEquals("Failure - total relations should be zero" , graph.relations(), 0);
    }

    @Test
    public void findNode(){

        GraphList<Integer> graph = new GraphList<>();

         for(int i = 0 ; i < 5; i ++){
             if(i % 2 == 0 )
                 graph.add(i);
         }

        assertNotNull("Failure - Node should not be null",graph.findFirst(0));

        assertNull("Failure - Node should be null", graph.findFirst(3));
    }

    @Test
    public void findAllNodes(){

        GraphList<Integer> graph = new GraphList<>();

        for(int i = 0 ; i < 5; i ++){
           graph.add(1);
        }

        assertEquals("Failure - List size is not expected",graph.findAll(1).size(), 5 );

        assertEquals("Failure - List size should be zero",graph.findAll(2).size(), 0 );
    }

    @Test
    public void deleteElement(){

        GraphList<Integer> graph = new GraphList<>();

        for(int i = 0; i < 8; i++){
            graph.add(i);
        }

        WeightedRelation<Integer> weightedRelation = new WeightedRelation<>(Direction.NO_DIRECTION);

        for(int i = 1; i < 8; i++ ){
            graph.addEdge(0, i, weightedRelation);
        }

        graph.deleteFirst(0);

        assertNull("Failure - Node should be null", graph.getNode(0));

        assertEquals( "Failure - Graph should have no relations", graph.relations(), 0);

        for(Integer id : graph.graphIds()){
            assertEquals("Failure - Node should have no relations ",graph.getNode(id).totalRelations(), 0);
        }
    }

    @Test
    public void graphIsDirected(){

        GraphList<Integer> graph = new GraphList<>();

        for(int i = 0 ; i < 10; i++){
            graph.add(i);
        }

        WeightedRelation<Integer> weightedRelation = new WeightedRelation<>(Direction.NO_DIRECTION);

        graph.addEdge(0 , 1, weightedRelation);
        graph.addEdge(1 , 2, weightedRelation);
        graph.addEdge(2 , 3, weightedRelation);
        graph.addEdge(3 , 4, weightedRelation);
        graph.addEdge(4 , 5, weightedRelation);
        graph.addEdge(5 , 6, weightedRelation);
        graph.addEdge(7 , 8, weightedRelation);


        assertFalse("Failure - graph should not have directions" ,graph.hasDirectedNodes());

        weightedRelation = new WeightedRelation<>(Direction.OUT_GOING);
        graph.addEdge(8, 9, weightedRelation);

        assertTrue("Failure - graph should have a directed edge ", graph.hasDirectedNodes());
    }


    @Test
    public void noEdgeNodes(){

        GraphList<Integer> graph = new GraphList<>();

        for(int i = 0 ; i < 10; i++){
            graph.add(i);
        }

        assertEquals("Failure - unequal number of nodes with no edges",graph.getNoEdgeNodes().size(), 10);
    }

    @Test
    public void predecessors(){

        GraphList<Integer> graph = new GraphList<>();

        for(int i = 0 ; i < 10; i++){
            graph.add(i);
        }

        WeightedRelation<Integer> weightedRelation = new WeightedRelation<>(Direction.INCOMING);

        for(int i  = 1; i < 10; i++){
            graph.addEdge(0, i, weightedRelation);
        }

        assertEquals("Failure - unequal number of predecessor nodes", graph.predecessors(0, false).size(), 9);
    }

    @Test
    public void successors(){

        GraphList<Integer> graph = new GraphList<>();

        for(int i = 0 ; i < 10; i++){
            graph.add(i);
        }

        WeightedRelation<Integer> weightedRelation = new WeightedRelation<>(Direction.OUT_GOING);

        for(int i  = 1; i < 10; i++){
            graph.addEdge(0, i, weightedRelation);
        }

        assertEquals("Failure - unequal number of predecessor nodes", graph.successors(0, false).size(), 9);
    }

    @Test
    public void uniDirection(){

        GraphList<Integer> graph = new GraphList<>();

        for(int i = 0 ; i < 10; i++){
            graph.add(i);
        }

        WeightedRelation<Integer> weightedRelation = new WeightedRelation<>(Direction.UNI_DIRECTION);

        for(int i  = 1; i < 10; i++){
            graph.addEdge(0, i, weightedRelation);
        }

        assertEquals("Failure - unequal number of predecessor nodes", graph.uniDirectionEdges(0, false).size(), 9);
    }


}
