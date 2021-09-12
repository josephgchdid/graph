package Graph;

public class WeightedRelation<K> extends AbstractRelation{

    private K data = null;

    public WeightedRelation(K data , Direction direction) {
        super(direction);
        this.data = data;
    }

    public WeightedRelation(Direction direction){
        super(direction);
    }

    public WeightedRelation(K data , String relationName, Direction direction) {
        super(relationName, direction);
        this.data = data;
    }

    public WeightedRelation(){
        super();
    }

    public K data() {
        return data;
    }

    public void data(K data) {
        this.data = data;
    }

    /**
     *
     * @return Returns the relation object but the direction is reversed
     */
    public WeightedRelation<K> reverseDirection(){

        //easiest way, otherwise it'll flip both u and v

        Direction direction = this.direction();

        if(direction == Direction.NO_DIRECTION || direction == Direction.UNI_DIRECTION){
            return new WeightedRelation<K>(
                    this.data,
                    relationName(),
                    direction);
        }

        return new WeightedRelation<K>(
                this.data,
                relationName(),
                direction() == Direction.INCOMING ?
                        Direction.OUT_GOING
                        :
                        Direction.INCOMING);
    }

    @Override
    public String toString() {
        return "Relation{" +
                " data= " + data +
                ",relation= " + relationName() +
                ",direction= " + direction() +
                '}';
    }
}
