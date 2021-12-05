package instance.world.cells.State;


import instance.world.cars.Car;
import instance.world.cells.Cell;

public abstract class DangerZoneHandler implements Cloneable {

    private boolean availability;

    public DangerZoneHandler() {
        this.availability = true;
    }

    public boolean isAvailable() {
        return availability;
    }

    public abstract boolean exitedDangerZone();

    public abstract boolean enterTheDangerZone(Car car, Cell cell);

    protected void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public DangerZoneHandler clone(){
        try {
            DangerZoneHandler dangerZoneHandler = (DangerZoneHandler) super.clone();
            return dangerZoneHandler;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
