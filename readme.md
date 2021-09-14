# Graph Data Structure
## Graph implementaion in Java

### Types of graph
|Graph| Explanation|
|----|---|
|GraphList<T>|  Graph where each node can have many Edges with other nodes
|GraphMap<T> | Graph where a node has only one relation with another node (Any Direction)  (node1)->(node2) ` cannot create anymore relations between node 1 and node 2 in any direction` |

`Note : The graph can have directed nodes and undirected nodes at the same time `

### usage :
```
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

```
the example above creates the following graph

![Alt text](screenshot/graph.png?raw=true "Title")
# DOCS

|Function|Parameters|Description|Return data type|
|--------|-----------|--------------|------|
|int add(T element) |element The data the node will hold | Creates a new node with no edges| `int` The node's id|
|add(K firstElement, K secondElement, Relation relation)|<ul><li>firstElement The data of the first node</li><li>secondElement The data of the second node</li><li>relation The relation between them</li></ul>|creates two nodes and creates an edge between them|`int[]` array containing The node ids, arr[0] is the firstElement arr[1] is the secondElement|
| addEdge(int u, int v, AbstractRelation relation) |  <ul><li>u The first node id </li><li>v The second node id</li><li>relation The relation between them</li></ul>| Creates an edge between two nodes, does <b>nothing</b> if node does not exist| `void`
|  removeEdge(int u, int v)|<ul><li>u The first node id</li><li>v The second node id</li></ul> |Removes an edge between two nodes | `void`
|T findFirst(K element)| element the element to search for| Finds the first node that contains `element` | `Node <T>` object, `null` if none found
|List `<T>` findAll(K element)|element The element to search for| Finds all nodes that contains `element`|List of `Node <T>` objects, the list is empty if none found
|deleteAll()| `none`| deletes the graph and resets data| `void`
| deleteFirst(T element) | element The element to search for|Delete the first node that contains `element`  and all its relations| `void`
|deleteById(int id)|  id The node id| Delete a node by its id and all its relations|`void`
|updateElement(T element, T data)| <ul><li>element The element to search for</li><li>data The new data</li></ul>| Update the first node that contains `element`| `void`
|updateById(int id, K data)|<ul><li>The element id</li><li>data The new data</li></ul>|Update the node by its id|`void`
|print()|`none`|print the graph to the console|`void`
|int size()|`none`|get the total nodes in the graph|`int`|
|currentId()|`none`| get the current id value of the graph| `int`
|T getNode(int id)|id Node id|Get a node by its id|`Node <T>` object|
|HashMap`<Integer, T>` graph()|`none`| returns a copy of the graph data structure, not the object| `HashMap<Integer, T>` |
|List`<T>`getNoEdgeNodes()| `none`|Get all nodes that have no edges| List of Node's
|boolean hasEdge(int u, int v)|<ul><li>firstElement The data of the first node</li><li>secondElement The data of the second node</li></ul>| check if two nodes are connected|`boolean`
| List`<Integer>` graphIds()|`none`| get all node ids in the graph| `List <Integer>`
|int relationsPerNode()|`none`| get max relations allowed per node| `int`
|int relationsPerNode(int num)|num number of relations| set max relations allowed per node| `void`
|boolean hasDirectedNodes()|`none`|checks if there are any directed relations in the graph| `boolean`
|Set`<T>` predecessors(int id, boolean includeSelf)|<ul ><li>id The node id</li><li>includeSelf whether to include loop relations or not (node that has a relation with itself)</li></ul>|Returns all nodes in this graph adjacent to node which can be reached by traversing node's <i>incoming</i> edges. |`Set<Node<T>>` or Empty Set if no Node or Relation is found
|Set`<T>` successors(int id, boolean includeSelf)|<ul ><li>id The node id</li><li>includeSelf whether to include loop relations or not (node that has a relation with itself)</li></ul>|Returns all nodes in this graph adjacent to node which can be reached by traversing node's <i>outgoing</i> edges. |`Set<Node<T>>` or Empty Set if no Node or Relation is found
|Set`<T>` uniDirectionEdges(int id, boolean includeSelf)|<ul ><li>id The node id</li><li>includeSelf whether to include loop relations or not (node that has a relation with itself)</li></ul>|Returns all nodes in this graph adjacent to node which can be reached by traversing node's <i>uni direction</i> edges. |`Set<Node<T>>` or Empty Set if no Node or Relation is found


