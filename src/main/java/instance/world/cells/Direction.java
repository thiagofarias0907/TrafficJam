package instance.world.cells;

public enum Direction {

    NONE (0),
    DOWN (3),
    UP (1),
    LEFT (4),
    RIGHT(2),
    UNDEF(5)
    ;

    public int value;

    Direction(int value) {
        this.value = value;
    }

}
