package instance.world;

import instance.world.cars.Car;
import instance.world.cells.Cell;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class World implements Runnable {

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

        Thread thread = new Thread(this);
        thread.start();

    }

    public WorldDrawable getDrawable() {
        return drawable;
    }

    @Override
    public void run() {
        for (Car car: this.cars) {
            Random rand = new Random();
            enterPoints.get(rand.nextInt(enterPoints.size())).enterThisRoad(car);
            Thread thread = new Thread(car);
            car.setSpeedInMs(rand.nextInt(1000,3000));
            thread.start();
        }
    }
}
