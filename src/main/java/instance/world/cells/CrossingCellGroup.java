package instance.world.cells;

import instance.world.cars.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class CrossingCellGroup{

    private List<Cell> cellList;
    private boolean carIsCrossing;
    private Semaphore mutex;
    private Car car;

    public CrossingCellGroup() {
        this.cellList = new ArrayList<>();
        this.mutex = new Semaphore(1);
        this.carIsCrossing = false;
    }

    public synchronized void enterThisCrossing(Car car) {
        if (!carIsCrossing) {
            try {
                mutex.acquire(1);
                this.car = car;
                carIsCrossing = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void exitThisCrossing() {
        this.carIsCrossing = false;
        this.car = null;
        mutex.release();
    }


    public List<Cell> getCellList() {
        return cellList;
    }

    public boolean isCarIsCrossing() {
        return carIsCrossing;
    }

    public Car getCar() {
        return car;
    }
}
