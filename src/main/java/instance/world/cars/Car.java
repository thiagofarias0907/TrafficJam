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

    private void moveToTheNextRoad(){
        if(currentRoad!=null){
            Cell cell = currentRoad.getPaths()[ThreadLocalRandom.current().nextInt(0, currentRoad.getPaths().length)];
            cell.enterThisRoad(this);
        }

    }


    @Override
    public void run() {

      while (true){
          try {
              Thread.sleep(speedInMs);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }

          moveToTheNextRoad();
      }

    }



    public CarDrawing getDrawing() {
        return drawing;
    }

    public Cell getCurrentRoad() {
        return currentRoad;
    }

    public void setCurrentRoad(Cell currentRoad) {
        this.currentRoad = currentRoad;
    }

    public void setSpeedInMs(long speedInMs) {
        this.speedInMs = speedInMs;
    }
}
