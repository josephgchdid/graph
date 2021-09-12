package Graph;

import java.util.HashMap;

/**
 *
 * Creates a graph where a node can have only one edge
 * with another node
 * @param <K> The Graph Node's data type
 */
public class GraphMap<K> extends AbstractGraph<NodeMap<K>, K>{

    public GraphMap(){}

    @Override
    protected NodeMap<K> create(K element, int id) {
       return new NodeMap<>(element,id);
    }

    @Override
    GraphMap<K> copy() {
        return null;
    }

    @Override
    GraphMap<K> copy(HashMap<Integer, NodeMap<K>> graph) {

        return null;

    }


}

