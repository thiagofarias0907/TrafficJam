package instance.world;

import instance.world.cars.Car;
import instance.world.cars.CarInitializer;
import instance.world.cells.Cell;

import java.util.*;

public class World {

    private WorldDrawable drawable;
    private HashMap<String, Cell> grid;
    private LinkedList<Car> cars;
    List<Cell> enterPoints;

    int carsCount = 0;

    public World(WorldDrawable drawable, HashMap<String, Cell> grid, List<Cell> enterPoints) {
        this.drawable = drawable;
        this.grid = grid;
        this.cars = cars;
        this.enterPoints = enterPoints;
    }

    public void onConstruction(){
        CarInitializer carInitializer = new CarInitializer(cars, enterPoints);
        carInitializer.initialize();
    }

    public WorldDrawable getDrawable() {
        return drawable;
    }

    public synchronized void addCarInWorld(){
        carsCount++;
    }

    public synchronized void subtractCarInWorld(){
        carsCount--;
    }

    public synchronized int getCarsCount() {
        return carsCount;
    }


}
