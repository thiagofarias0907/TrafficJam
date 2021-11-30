package instance.world.cars;

public class Transform {

    private int[] position;
    private int rotation;


    public int[] getPosition() {
        return position;
    }

    public int getRotation() {
        return rotation;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }
}
