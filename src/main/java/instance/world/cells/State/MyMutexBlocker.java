package instance.world.cells.State;


import instance.world.cars.Car;
import instance.world.cells.Cell;

import java.util.concurrent.ThreadLocalRandom;

/*
    when the car will attempt to enter, it will first check if the road is available

 */
public class MyMutexBlocker extends DangerZoneHandler {


    @Override
    public boolean exitedDangerZone() {

      return true;
    }

    @Override
    public boolean enterTheDangerZone(Car car, Cell cell) {

        synchronized (this){

            final String[] valuesInKey = car.getCurrentRoad().getId().split("\s");

            System.out.println("Car: "+ car+" Entering road: "+cell);

            try {
                Thread.sleep(car.getSpeedInMs());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            car.getDrawing().setxPos(Integer.parseInt(valuesInKey[1]));
            car.getDrawing().setyPos(Integer.parseInt(valuesInKey[0]));
            car.setCurrentRoad(cell);




        }

        return false;
    }
}
