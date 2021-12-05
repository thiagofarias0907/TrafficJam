package instance.world.cells.State;


import instance.world.cars.Car;
import instance.world.cells.Cell;

public abstract class DangerZoneHandler implements Cloneable {

    private Cell cell;

    public abstract void moveCarToThisCell(Car car);

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
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
