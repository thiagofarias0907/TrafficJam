package instance.world;

import instance.world.cars.Car;
import instance.world.cars.CarInitializer;
import instance.world.cells.Cell;
import instance.world.cells.CrossingCellGroup;

import java.util.*;

public class World {

    private WorldDrawable drawable;
    private HashMap<String, Cell> grid;
    private LinkedList<Car> cars;
    List<Cell> enterPoints;
    List<CrossingCellGroup> crossingCellGroupList;

    int carsCount = 0;
    private CarInitializer carInitializer;

    public World(WorldDrawable drawable, HashMap<String, Cell> grid, List<Cell> enterPoints, List<CrossingCellGroup> crossingCellGroupList) {
        this.drawable = drawable;
        this.grid = grid;
        this.cars = cars;
        this.enterPoints = enterPoints;
        this.crossingCellGroupList = crossingCellGroupList;
    }

    public void onConstruction(){
        carInitializer = new CarInitializer(cars, enterPoints);
        Thread initializerThread = new Thread(carInitializer);
        initializerThread.start();
    }

//    public synchronized void stopCarInitializer(){
//        carInitializer.stop();
//    }

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

    public List<CrossingCellGroup> getCrossingCellGroupList() {
        return crossingCellGroupList;
    }

    public CrossingCellGroup getCrossingGroup(Cell cell){
        for (CrossingCellGroup crossingCellGroup : crossingCellGroupList) {
            if (crossingCellGroup.getCellList().contains(cell))
                return crossingCellGroup;
        }
        return null;
    }
}
