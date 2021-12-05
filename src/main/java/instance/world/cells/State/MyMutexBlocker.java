package instance.world.cells.State;


import instance.world.cars.Car;
import instance.world.cells.Cell;

import java.util.concurrent.ThreadLocalRandom;

/*
    when the car will attempt to enter, it will first check if the road is available
 */
public class MyMutexBlocker extends DangerZoneHandler {



    @Override
    public void moveCarToThisCell(Car car) {

        synchronized (this){



            try {
                Thread.sleep(car.getSpeedInMs());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            car.setCurrentRoad(super.getCell());


        }

    }
}
