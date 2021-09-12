package Graph;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.ArrayList;
import java.util.List;

/**
 * return type of GraphList node
 * @param <T> Node data type
 */
public class NodeList<T> extends Node<T> {


    Multimap<Integer, AbstractRelation> vertex;

    public NodeList() {

        super();

        vertex = ArrayListMultimap.create();
    }

    public NodeList(T data, int ID) {

        super(data, ID);

        vertex = ArrayListMultimap.create();
    }

    @Override
    protected boolean add(int id, AbstractRelation relation) {

        boolean canAddRelation = increaseRelations(1);

        if(canAddRelation){
            vertex.put(id, relation);
        }

        return canAddRelation;
    }

    @Override
    protected void remove(int idToRemove) {

         int beforeSize = vertex.size();

         vertex.removeAll(idToRemove);

         int afterSize = vertex.size();

         int totalRemoved = beforeSize - afterSize;

         decreaseRelations(totalRemoved);

    }

    @Override
    public List<AbstractRelation> relations(int key) {
        return vertex.get(key).stream().toList();
    }

    @Override
    public int size() {
        return vertex.size();
    }

    @Override
    public List<Integer> relations() {
        return new ArrayList<>(vertex.keySet());
    }
}
