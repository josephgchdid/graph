package Graph;

public class Relation extends AbstractRelation{

    public Relation(){
        super();
    }

    public Relation(Direction direction){
        super(direction);
    }

    public Relation( String relationName, Direction direction) {
        super(relationName, direction);
    }

    @Override
    AbstractRelation reverseDirection() {
        //easiest way, otherwise it'll flip both u and v

        Direction direction = this.direction();

        if(direction == Direction.NO_DIRECTION || direction == Direction.UNI_DIRECTION){
            return new Relation(
                    relationName(),
                    direction);
        }

        return new Relation(
                relationName(),
                direction() == Direction.INCOMING ?
                        Direction.OUT_GOING
                        :
                        Direction.INCOMING);
    }
}
