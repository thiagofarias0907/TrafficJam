package instance.world;

import instance.world.cars.Car;
import instance.world.cells.Cell;
import java.util.HashMap;
import java.util.LinkedList;

public class World  {

    private WorldDrawable drawable;
    private HashMap<String, Cell> grid;
    private LinkedList<Car> cars;

    public World(WorldDrawable drawable, HashMap<String, Cell> grid, LinkedList<Car> cars) {
        this.drawable = drawable;
        this.grid = grid;
        this.cars = cars;
    }

    public WorldDrawable getDrawable() {
        return drawable;
    }


}
