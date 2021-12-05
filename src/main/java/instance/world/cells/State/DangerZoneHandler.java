package instance.world.cells.State;


import instance.world.cars.Car;
import instance.world.cells.Cell;

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
