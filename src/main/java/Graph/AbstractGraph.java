package Graph;

import Graph.Query.Query;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

abstract  class AbstractGraph<T extends Node<K>, K> {

    protected HashMap<Integer, T> graph = new HashMap<>();

    protected final AtomicInteger atomicId = new AtomicInteger();

    protected  int totalNodes = 0;

    protected int totalRelations = 0;

    protected abstract T create(K element, int id);

    protected int defaultMaxRelationPerNode = 100;

    public final Query<K> query = new Query<>();

    abstract AbstractGraph<T, K> copy();

    abstract AbstractGraph<T, K> copy(HashMap<Integer, T> graph);

    /**
     * Creates a new node with no edges
     * @param element The data the node will hold
     * @return The node's id
     */
    public int add(K element)  {

        if(element == null){
            throw new NoElementException("Graph add(T element) : element cannot be null");
        }

        int id = atomicId.getAndIncrement();

        T node = create(element, id);

        node.maxRelations(defaultMaxRelationPerNode);

        graph.put(id, node);

        this.totalNodes++;

        return id;
    }


    /**
     * creates two nodes and creates an edge between them
     * @param firstElement The data of the first node
     * @param secondElement The data of the second node
     * @param relation The relation between them
      *@return int[] array containing The node ids, arr[0] is the firstElement arr[1] is the secondElement
     */
    public int[] add(K firstElement, K secondElement, AbstractRelation relation){

        if(firstElement == null || secondElement == null){
            throw new NoElementException("Graph add(T firstElement, T secondElement) : elements cannot be null");
        }

        int u = add(firstElement);

        int v = add(secondElement);

        graph.get(u).add(v, relation);

        graph.get(v).add(u,  relation.reverseDirection());

        this.totalRelations++;

        return new int[]{u,v};
    }


    /**
     * Creates an edge between two nodes, does <b>nothing</b> if node does not exist
     * @param u The first node's id
     * @param v The second node's id
     * @param relation Relation object to describe the relation between the nodes
     */
    public void addEdge(int u, int v, AbstractRelation relation){

        checkOutOfBound(u,v,"addEdge(int u, int v...)");

        T nodeU = graph.get(u);

        T nodeV = graph.get(v);

        if(nodeU == null || nodeV == null)
            return;

        if(!nodeU.isAtCapacity() && !nodeV.isAtCapacity()){

            nodeU.add(v, relation);

            nodeV.add(u, relation.reverseDirection());

            graph.replace(u, nodeU);

            graph.replace(v, nodeV);

        }

        this.totalRelations++;
    }

    /**
     * Removes an edge between two nodes
     * @param u The first node's id
     * @param v The second node's id
     */
    public void removeEdge(int u, int v){

        checkOutOfBound(u,v, "removeEdge(int u, int v)");

        if(totalRelations == 0)
            return;

        if(!graph.containsKey(u) && !graph.containsKey(v) && !hasEdge(u, v))
            return;

        graph.get(u).remove(v);

        graph.get(v).remove(u);

        totalRelations--;
    }

    /**
     * Finds the first node that contains `element`
     * @param element The element to search for
     * @return Node <T> object, null if none found
     */
    public T findFirst(K element){

        for(Integer key : graph.keySet()){

            if(graph.get(key).data().equals(element))
                return graph.get(key);
        }

        return null;

    }

    /**
     * Finds all nodes that contains `element`
     * @param element The element to search for
     * @return List of Node <T> objects, the list is empty if none found
     */
    public List<T> findAll(K element){

        List<T> list = new ArrayList<>();

        for(Integer key : graph.keySet()){
            if(graph.get(key).data().equals(element))
                list.add(graph.get(key));
        }

        return list;
    }

    /**
     * deletes the graph and resets data
     */
    public void deleteAll(){

        graph.clear();

        atomicId.set(0);

        totalNodes = 0;

        totalRelations = 0;
    }

    /**
     * Delete the first node that contains `element`
     * and all its relations
     * @param element The element to search for
     */
    public void deleteFirst(K element){

        T node = findFirst(element);

        // no element found
        if(node == null){
            return;
        }

        int id = node.id();

        List<Integer> iterator = node.relations();

        for(Integer idToRemoveFrom : iterator ){
            graph.get(idToRemoveFrom).remove(id);
            totalRelations--;
        }

        graph.remove(id);

        totalNodes--;
    }

    /**
     * Delete a node by its id and all its relations
     * @param id The node's id
     */
    public void deleteById(int id){

        T node = graph.get(id);

        // no element found
        if(node == null){
            return;
        }

        List<Integer> iterator = node.relations();

        for(Integer idToRemoveFrom : iterator ){
            graph.get(idToRemoveFrom).remove(id);
            totalRelations--;
        }

        graph.remove(id);

        totalNodes--;
    }

    /**
     * Update the first node that contains `element`
     * @param element The element to search for
     * @param data The new data
     */
    public void updateElement(K element, K data){

        T node = findFirst(element);

        // no element found
        if(node == null){
            return;
        }

        node.data(data);

        graph.replace(node.id(), node);
    }

    /**
     * Update the node by its id
     * @param id The element's id
     * @param data The new data
     */
    public void updateById(int id, K data){

        T node = graph.get(id);

        // no element found
        if(node == null){
            return;
        }

        node.data(data);

        graph.replace(id, node);
    }

    /**
     * print the graph to the console
     */
    public void print(){

        if(graph.size() == 0){

            System.out.println("\n[ EMPTY GRAPH ]");

            return;
        }

        for(Integer key : graph.keySet()){

            System.out.print("\nAdjacency list of vertex with id [ " + key + " ]" );

            T node = graph.get(key);

            System.out.println();

            if(node.size() == 0){
                System.out.print("[ EMPTY ]");
            }
            else {

                List<Integer> iterator = node.relations();

                for(Integer relationKey : iterator ){

                    List<AbstractRelation> relations = node.relation(relationKey);

                    for(AbstractRelation relation :  relations){
                        System.out.printf("( %d %s %d ) ",key, relation.printDirection(),relationKey);
                    }

                }

            }

            System.out.print("\nData : [ " + node.data() + " ]\n");

        }
    }

    /**
     *
     * @return total nodes in the graph
     */
    public int size(){
        return totalNodes;
    }

    /**
     *
     * @return the current id value of the graph
     */
    public int currentId(){
        return atomicId.get();
    }


    /**
     *
     * @return total relations in the graph
     */
    public int relations(){
        return totalRelations;
    }

    /**
     * Get a node by its id
     * @param id Node id
     * @return Node <T> object
     */
    public T getNode(int id){
        return graph.get(id);
    }


    /**
     * returns a copy of the graph data structure, not the object
     * @return Hashmap <Integer, T>
     */
    public HashMap<Integer, T> graph(){
        return graph;
    }

    /**
     * Get all nodes that have no edges
     * @return List of Node's
     */
        public List<T> getNoEdgeNodes(){

        List<T> list = new ArrayList<>();

        for(Integer key : graph().keySet()){

            T node = graph.get(key);

            if(node.totalRelations() == 0)
                list.add(node);
        }

        return list;
    }

    /**
     * check if two nodes are connected
     * @param u First node's id
     * @param v Second node's id
     * @return boolean
     */
    public boolean hasEdge(int u, int v){

        if(u <  0 || v < 0){
            throw new NoElementException("Graph hasEdge(int u, int v) : indices cannot be less than 0");
        }

        if(u > totalNodes || v > totalNodes){
            throw new NoElementException("Graph hasEdge(int u, int v) : indices cannot be greater than graph size");
        }


        return graph.get(u).relations().stream().anyMatch(el -> el == v);
    }

    /**
     *
     * @return all node ids in the graph
     */
    public List<Integer> graphIds(){
        return new ArrayList<>(graph.keySet());
    }


    /**
     * max relations allowed per node
     * @return int
     */
    public int relationsPerNode(){
        return defaultMaxRelationPerNode;
    }

    /**
     * set max relations allowed per node
     * @param num number of relations
     */
    public void relationsPerNode(int num){

        if(num <= 0 ){
            defaultMaxRelationPerNode = Integer.MAX_VALUE;
            return;
        }

        defaultMaxRelationPerNode = num;
    }

    private void checkOutOfBound(int u, int v,String method){

        if(u <  0 || v < 0){
            throw new NoElementException(String.format("Graph %s : indices cannot be less than 0", method));
        }

        if( u > totalNodes || v > totalNodes){
            throw new NoElementException(String.format("Graph %s : indices cannot be greater than graph size",method));
        }
    }

    //get ids from graph and set them all to false ( not visited )
    private HashMap<Integer, Boolean> visitedMap(){

        return   (HashMap<Integer, Boolean>) graphIds()
                    .stream()
                    .collect(
                        Collectors
                        .toMap(Function.identity(), (e) -> Boolean.FALSE));
    }


    /**
     * checks if there are any directed relations in the graph
     * @return boolean
     */
    public boolean hasDirectedNodes(){

        HashMap<Integer, Boolean> visited = visitedMap();

        for(Integer key : visited.keySet()){

            if(!visited.get(key)){

              boolean hasDirection =  isDirectedDfs(key, visited);

              if(hasDirection)
                  return true;
            }

        }

        return false;
    }


    private boolean isDirectedDfs(int key, HashMap<Integer, Boolean> visited){

       visited.replace(key, true);

        T node =   graph.get(key);

        //the current node has edges at all
        if(node.totalRelations() != 0){

            for(Integer element : node.relations()){

                //stream filter for direction other than NO_DIRECTION
                boolean hasDirection =
                        graph.get(key)
                        .relation(element)
                        .stream()
                        .anyMatch(rel -> rel.direction() != Direction.NO_DIRECTION);

                if(hasDirection)
                    return true;

                if(!visited.get(element)) {
                    isDirectedDfs(element, visited);
                }
            }
        }

        //no direction found
        return false;
    }

    /**
     * Returns all nodes in this graph adjacent to node
     * which can be reached by traversing node's <i>incoming</i>
     * edges.
     *
     * @param id The node's id
     * @param includeSelf whether to include loop relations or not
     *                    (node that has a relation with itself)
     * @return Set of Nodes or Empty Set if no Node or Relation is found
     */
    public Set<T> predecessors(int id, boolean includeSelf){

        Predicate<AbstractRelation> filter =
                rel -> rel.direction() == Direction.INCOMING;


        return getRelationNodes(id,includeSelf, filter);
    }

    /**
     * Returns all nodes in this graph adjacent to node
     * which can be reached by traversing node's <i>outgoing</i>
     * edges.
     *
     * @param id The node's id
     * @param includeSelf whether to include loop relations or not
     *                    (node that has a relation with itself)
     * @return Set of Nodes or Empty Set if no Node or Relation is found
     */
    public Set<T> successors(int id, boolean includeSelf){

        Predicate<AbstractRelation> filter =
                rel -> rel.direction() == Direction.OUT_GOING;

        return getRelationNodes(id,includeSelf, filter);
    }

    /**
     * Returns all nodes in this graph adjacent to node
     * which can be reached by traversing node's <i>uni direction</i>
     * edges.
     *
     * @param id The node's id
     * @param includeSelf whether to include loop relations or not
     *                    (node that has a relation with itself)
     * @return Set of Nodes or Empty Set if no Node or Relation is found
     */
    public Set<T> uniDirectionEdges(int id, boolean includeSelf){

        Predicate<AbstractRelation> filter =
                rel -> rel.direction() == Direction.UNI_DIRECTION;

        return getRelationNodes(id,includeSelf, filter);
    }

    private Set<T> getRelationNodes(int id, boolean includeSelf,  Predicate<AbstractRelation> filter){

        T node =  graph.get(id);

        //id not found
        if(node == null)
            return new HashSet<>();

        Set<T> nodeSet = new HashSet<>();

        //i >= 0 is a small trick to select all ids if self included
        Predicate<Integer> includeSelfFilter = i -> includeSelf ?   i >= 0 : i != id;

        List<Integer> nodeRelationsId =
                node
                .relations()
                .stream()
                .filter(includeSelfFilter)
                .toList();

        for(Integer relationId : nodeRelationsId){

            List<AbstractRelation> outGoingRelations =
                    node
                    .relation(relationId)
                    .stream()
                    .filter(filter).toList();

            if(outGoingRelations.size() !=0)
                nodeSet.add(graph.get(relationId));
        }
        return nodeSet;
    }

    @Override
    public String toString() {
        return "AbstractGraph{" +
                "graph=" + graph +
                ", atomicId=" + atomicId.get() +
                ", totalNodes=" + totalNodes +
                ", totalRelations=" + totalRelations +
                ", defaultMaxRelationPerNode=" + defaultMaxRelationPerNode +
                '}';
    }

    /* SOME DAY
    //to do, optimize distance calculations and fix bugs00
    public void shortestPath(int start, int target){

        checkOutOfBound(start, target, "shortestPath(int start, int target)");

        //get startNode and end Node
        T startNode = graph.get(start);

        T targetNode = graph.get(target);

        //no nodes Found
        if(startNode == null || targetNode == null) {
            return;
        }

        List<T> path = new ArrayList<>();

        //store startNode ids in list instead of object
        List<Integer> openList = new ArrayList<>();

        Set<Integer> closedList = new HashSet<>();

        //begin with the starting startNode
        openList.add(startNode.id());

        path.add(startNode);

        //Calculate heuristic distance of start vertex to destination (h)

        int fCost = startNode.gCost() + 12;

        T node = startNode;

        System.out.println("calculating ");
        while (node != targetNode){

            System.out.println(node.id());
            //get all nodes which have an outgoing direction or no direction
            Set<T> walkableNeighbours = successors(startNode.id(), false);

            for(T neighbour : walkableNeighbours){

                if(!closedList.contains(neighbour.id()) && !openList.contains(neighbour.id())){
                    openList.add(neighbour.id());
                }

                int newCostToNeighbour = startNode.gCost() + getDistance(startNode, neighbour);

                neighbour.gCost( newCostToNeighbour);

                neighbour.hCost(getDistance(neighbour, targetNode));

                int newFCost = neighbour.fCost();

                if(newFCost < fCost ){

                    fCost = newFCost;

                    path.add(node);

                    System.out.println("found good node " + node.id());
                }
            }

            closedList.add(node.id());

           // look for a better startNode
            int lowestFNodeId = node.id();

            for(int i = 0; i < openList.size(); i++){

                T optimalNode = graph.get(openList.get(i));

                if(optimalNode.fCost() < node.fCost()){
                    lowestFNodeId = optimalNode.id();
                }
            }

            openList.remove((Integer) lowestFNodeId);

            node = graph.get(lowestFNodeId);

            System.out.println("lower node " + node.id() + " fCost " + fCost);
        }

        System.out.println(path);
    }

    private int getDistance(T node, T neighbour){
        return 1; // to do
    }*/
}
