package Graph;

public class Relation <K> extends AbstractRelation{

    private K data = null;

    public Relation(K data ,  Direction direction) {
        super(direction);
        this.data = data;
    }

    public Relation(K data ,String relationName, Direction direction) {
        super(relationName, direction);
        this.data = data;
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
    public Relation<K> reverseDirection(){

        //easiest way, otherwise it'll flip both u and v
        return new Relation<K>(
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
