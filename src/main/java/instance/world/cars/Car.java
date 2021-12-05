package instance.world.cars;

import instance.world.cells.Cell;
import java.util.concurrent.ThreadLocalRandom;

public class Car implements Runnable{

    private CarDrawing drawing;
    private Cell currentRoad;
    private long speedInMs;

    public Car(CarDrawing drawing, long speedInMs) {
        this.drawing = drawing;
        this.speedInMs = speedInMs;
    }

    public boolean moveToTheNextRoad(){

        Cell cell = currentRoad.getPaths()[ThreadLocalRandom.current().nextInt(0, currentRoad.getPaths().length)];

        cell.getDangerZoneHandler().enterTheDangerZone(this, cell);

        return false;

    }

    @Override
    public void run() {

      while (true){
          moveToTheNextRoad();
      }

    }

    public Cell getCurrentRoad() {
        return currentRoad;
    }

    public long getSpeedInMs() {
        return speedInMs;
    }

    public void setCurrentRoad(Cell currentRoad) {
        this.currentRoad = currentRoad;
    }

    public CarDrawing getDrawing() {
        return drawing;
    }

    @Override
    public String toString() {
        return "Car{" +
                "drawing=" + drawing +
                ", currentRoad=" + currentRoad +
                '}';
    }
}
