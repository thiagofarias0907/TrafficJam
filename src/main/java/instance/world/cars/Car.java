package instance.world.cars;

import instance.world.cells.Cell;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Car implements Runnable{

    private Cell currentRoad;
    private long speedInMs;

    public Car(long speed) {
        this.speedInMs = speed;
    }



    public boolean moveToTheNextRoad(){

        Cell cell = currentRoad.getPaths()[ThreadLocalRandom.current().nextInt(0, currentRoad.getPaths().length)];


        if(cell.getDangerZoneHandler().isAvailable()){
            cell.getDangerZoneHandler().enterTheDangerZone();
            currentRoad.getDangerZoneHandler().exitedDangerZone();
            currentRoad = cell;
        }

        return false;

    }

    @Override
    public void run() {
        try {
            Thread.sleep(speedInMs);
            moveToTheNextRoad();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Cell getCurrentRoad() {
        return currentRoad;
    }

    public void setCurrentRoad(Cell currentRoad) {
        this.currentRoad = currentRoad;
    }
}
