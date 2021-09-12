package Graph;

import java.util.HashMap;

/**
 * Creates a graph where a node can have many edges with other nodes
 * @param <T> The data type of the graph
 */
public class GraphList<T> extends AbstractGraph<NodeList<T>, T> {

    public GraphList(){}

    @Override
    protected NodeList<T> create(T element, int id) {
        return new NodeList<>(element, id);
    }

    @Override
    public GraphList<T> copy(){

        return null;
    }

    @Override
    public GraphList<T> copy(HashMap<Integer, NodeList<T>> graph) {

        return null;
    }


}
