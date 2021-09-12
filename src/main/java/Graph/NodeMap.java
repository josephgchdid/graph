package Graph;

import java.util.*;

/**
 * return type of GraphMap node
 * @param <T> Node data type
 */
public class NodeMap<T> extends Node<T> {

    private HashMap<Integer, AbstractRelation> vertex;

    public NodeMap(){
        super();
        vertex = new HashMap<>();
    }

    public NodeMap(T data , int id){

        super(data, id);

        vertex = new HashMap<>();
    }


    @Override
    protected boolean add(int id, AbstractRelation relation) {

        boolean canAddRelation = !vertex.containsKey(id);

        if(canAddRelation){

            increaseRelations(1);

            vertex.put(id, relation);
        }

        return canAddRelation;
    }

    @Override
    protected void remove(int idToRemove) {

        int beforeSize = vertex.size();

        vertex.remove(idToRemove);

        int afterSize = vertex.size();

        int totalRemoved = beforeSize - afterSize;

        decreaseRelations(totalRemoved);

    }

    @Override
    public List<AbstractRelation> relations(int key) {
       return List.of(vertex.get(key));
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
