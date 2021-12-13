package instance.world.cells;

import instance.world.cars.Car;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class CrossingCellGroup{

    private List<Cell> cellList;
    private Semaphore mutex;
    private Car car;

    public CrossingCellGroup() {
        this.cellList = new ArrayList<>();
        this.mutex = new Semaphore(1);
    }

    public void enterThisCrossing(Car car) {

        if(this.car == null) {
            try {
                mutex.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            this.car = car;
            System.out.println("Cruzamento " + this + "; Carro " + car.toString() + "; Entrada; " + LocalDateTime.now());
        }
    }

    public void exitThisCrossing() {
        System.out.println("Cruzamento " + this + "; Carro " + car.toString() + "; Saída; " + LocalDateTime.now());
        this.car = null;
        mutex.release();
    }


    public List<Cell> getCellList() {
        return cellList;
    }

    public Car getCar() {
        return car;
    }
}
