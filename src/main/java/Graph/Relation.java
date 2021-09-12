package Graph;


/**
 * Class That describes the relation between nodes
 * @param <K> the relation data type
 */
public class Relation<K> {

    private K data = null;

    private String relationName = "relation";

    private Direction direction;

    public Relation(K data, Direction direction){

        this.data = data;

        this.direction = direction;

    }

    public Relation(Direction direction){

        this.direction = direction;

    }

    public Relation(K data,String relationName, Direction direction){

        this.data = data;

        this.relationName = relationName;

        this.direction = direction;
    }

    // when creating an edge [ u -> v , v <- u ]
    public Relation<K> reverseDirection(){

        //easiest way, otherwise it'll flip both u and v
        return new Relation<K>(
                this.data,
                this.relationName,
                this.direction == Direction.RIGHT_TO_LEFT ?
                Direction.LEFT_TO_RIGHT
                :
                Direction.RIGHT_TO_LEFT);
    }

    public Relation(){}

    public K data() {
        return data;
    }

    public void data(K data) {
        this.data = data;
    }

    public String printDirection() {
       switch (direction){
           case LEFT_TO_RIGHT -> {
               return String.format("-[%s]->", relationName);
           }
           case RIGHT_TO_LEFT -> {
               return String.format("<-[%s]-", relationName);
           }

           case NO_DIRECTION -> {
               return String.format("-[%s]-", relationName);
           }
           case UNI_DIRECTION -> {
               return String.format("<-[%s]->", relationName);
           }
       }

       return String.format("-[%s]-", relationName);
    }

    public Direction direction(){ return this.direction; }

    public void direction(Direction direction) {
        this.direction = direction;
    }

    public String relationName() {
        return relationName;
    }

    public void relationName(String relationName) {
        this.relationName = relationName;
    }

    @Override
    public String toString() {
        return "Relation{" +
                "data=" + data +
                ", relationName='" + relationName + '\'' +
                ", direction=" + direction +
                '}';
    }
}