package instance.world;

import instance.world.cars.Car;
import instance.world.cars.CarInitializer;
import instance.world.cells.Cell;

import java.util.*;

public class World {

    private WorldDrawable drawable;
    private HashMap<String, Cell> grid;
    private LinkedList<Car> cars;


    public World(WorldDrawable drawable, HashMap<String, Cell> grid, LinkedList<Car> cars, List<Cell> enterPoints, List<Cell> exitPoints) {
        this.drawable = drawable;
        this.grid = grid;
        this.cars = cars;


        CarInitializer carInitializer = new CarInitializer(cars, enterPoints);
        carInitializer.initialize();
    }

    public WorldDrawable getDrawable() {
        return drawable;
    }




}
