package instance.world.cars;

import instance.world.cells.Cell;

public class Car implements Runnable{

    private Cell currentRoad;
    private float speedInMs;

    public Car(float speed) {
        this.speedInMs = speed;
    }



    public boolean moveToTheNextRoad(){
        //todo: random the index of this path, respecting the limits of this array;
        Cell cell = currentRoad.getPaths()[0];


        if(cell.getDangerZoneHandler().isAvailable()){
            cell.getDangerZoneHandler().enterTheDangerZone();
            currentRoad.getDangerZoneHandler().exitedDangerZone();
            currentRoad = cell;
        }

        return false;

    }

    @Override
    public void run() {

    }

    public Cell getCurrentRoad() {
        return currentRoad;
    }

    public void setCurrentRoad(Cell currentRoad) {
        this.currentRoad = currentRoad;
    }
}
