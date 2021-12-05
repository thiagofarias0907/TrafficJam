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

    public void moveToTheNextRoad(){

        if(currentRoad!=null){
            Cell cell = currentRoad.getPaths()[ThreadLocalRandom.current().nextInt(0, currentRoad.getPaths().length)];
            cell.getDangerZoneHandler().moveCarToThisCell(this);
        }


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
        final String[] valuesInKey = currentRoad.getId().split("\s");
        drawing.setxPos(Integer.parseInt(valuesInKey[1]));
        drawing.setyPos(Integer.parseInt(valuesInKey[0]));
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
