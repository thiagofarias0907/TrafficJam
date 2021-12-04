package instance.world;

import instance.world.cars.Car;
import instance.world.cells.Cell;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class World  {

    private WorldDrawable drawable;
    private HashMap<String, Cell> grid;
    private LinkedList<Car> cars;
    private List<Cell> enterPoints;
    private List<Cell> exitPoints;

    public World(WorldDrawable drawable, HashMap<String, Cell> grid, LinkedList<Car> cars, List<Cell> enterPoints, List<Cell> exitPoints) {
        this.drawable = drawable;
        this.grid = grid;
        this.cars = cars;
        this.enterPoints = enterPoints;
        this.exitPoints = exitPoints;
    }

    public WorldDrawable getDrawable() {
        return drawable;
    }


}
