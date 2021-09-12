package Graph;



import java.util.List;

public class Node<T>{

    private  T data;

    private  int ID;

    private int maxRelations = 100;

    private int totalRelations = 0;

    private int gCost = 0;

    private int hCost = 0;

    public Node(){}

    public Node(T data, int ID) {
        this.data = data;
        this.ID = ID;
    }

    public T data() {
        return data;
    }

    public void data(T data) {
        this.data = data;
    }

    /**
     *
     * @return max allowed relations of the node
     */
    public int maxRelations() {
        return maxRelations;
    }

    public void maxRelations(int maxRelations) {
        this.maxRelations = maxRelations;
    }

    /**
     *
     * @return the node's id
     */
    public int id(){
        return ID;
    }

    /**
     *
     * @return total relations the node has
     */
    public int totalRelations() {
        return totalRelations;
    }

    protected void id(int id){
        this.ID = id;
    }


    protected boolean add(int id, AbstractRelation relation) {
        return false;
    }


    protected void remove(int idToRemove) {

    }

    /**
     * Get Relations of connected node by its id
     * @param key The node's id
     * @return List of Relation objects
     */
    public List<AbstractRelation> relations(int key) {
        return null;
    }

    public int size() {
        return 0;
    }

    /**
     *
     * @return List of node ids that have a relation with the node
     */
    public List<Integer> relations() {
        return null;
    }

    /**
     *
     * @return if total relations is equal to max relations
     */
    public boolean isAtCapacity(){
        return totalRelations == maxRelations;
    }

    protected int increaseRelations(){
        return totalRelations;
    }

    protected boolean increaseRelations(int num){

        if(totalRelations == maxRelations){
            return false;
        }

        if(num >= maxRelations){
            totalRelations = maxRelations ;
            return true;
        }

        totalRelations += num;

        return true;
    }

    protected void decreaseRelations(){

        if(totalRelations == 0){
            return;
        }

        totalRelations--;
    }

    protected void decreaseRelations(int num){

        if(num >= maxRelations) {
            totalRelations = 0;
        }

        totalRelations -= num;

        if(totalRelations <= 0)
            totalRelations = 0;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", ID=" + ID +
                ", maxRelations=" + maxRelations +
                ", totalRelations=" + totalRelations +
                '}';
    }

    //A* data
    protected int hCost(){ return hCost; }

    protected void hCost(int hCost){ this.hCost = hCost;}

    protected void gCost(int gCost){ this.gCost = gCost; }

    protected int gCost(){ return gCost; }

    protected int fCost(){
        return hCost + gCost;
    }

    protected void resetCost(){

        this.hCost = 0;

        this.gCost = 0;
    }


}
