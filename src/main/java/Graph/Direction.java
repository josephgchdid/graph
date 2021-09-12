package Graph;

public enum Direction {

    OUT_GOING(0),

    INCOMING(1),

    NO_DIRECTION(2),

    UNI_DIRECTION (3);

    private final int value;

    Direction(int value){
        this.value = value;
    }

}
