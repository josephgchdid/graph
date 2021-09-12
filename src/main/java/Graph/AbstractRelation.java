package Graph;


/**
 * Class That describes the relation between nodes
 *
 */
abstract class AbstractRelation {

    private String relationName = "relation";

    private Direction direction;

    public AbstractRelation(Direction direction){

        this.direction = direction;

    }


    public AbstractRelation(String relationName, Direction direction){

        this.relationName = relationName;

        this.direction = direction;
    }

    // when creating an edge [ u -> v , v <- u ]



    public AbstractRelation(){}


    abstract AbstractRelation reverseDirection();

    public String printDirection() {
       switch (direction){
           case OUT_GOING -> {
               return String.format("-[%s]->", relationName);
           }
           case INCOMING -> {
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
        return "AbstractRelation{" +
                "relationName='" + relationName + '\'' +
                ", direction=" + direction +
                '}';
    }
}