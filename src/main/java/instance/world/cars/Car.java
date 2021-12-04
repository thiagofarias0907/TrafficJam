package instance.world.cars;

import instance.world.cells.Cell;

import java.util.Random;
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


        if(cell.getDangerZoneHandler().isAvailable()){

//                Thread.sleep(speedInMs);
                cell.getDangerZoneHandler().enterTheDangerZone();
                currentRoad.getDangerZoneHandler().exitedDangerZone();
                currentRoad = cell;
                return true;


        }

        return false;

    }

    @Override
    public void run() {

      while (true){
          moveToTheNextRoad();
//          drawing.draw();
      }

    }

    public Cell getCurrentRoad() {
        return currentRoad;
    }

    public void setCurrentRoad(Cell currentRoad) {
        this.currentRoad = currentRoad;
    }

    public CarDrawing getDrawing() {
        return drawing;
    }
}
