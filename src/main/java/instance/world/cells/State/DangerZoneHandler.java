package instance.world.cells.State;



public abstract class DangerZoneHandler implements Cloneable {


    @Override
    public DangerZoneHandler clone() {


        try {
            return (DangerZoneHandler) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return null;
    }
}
