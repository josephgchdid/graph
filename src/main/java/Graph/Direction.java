package Graph;

public enum Direction {

    LEFT_TO_RIGHT(0),

    RIGHT_TO_LEFT(1),

    NO_DIRECTION(2),

    UNI_DIRECTION (3);

    protected final int value;

    Direction(int value){
        this.value = value;
    }

}
