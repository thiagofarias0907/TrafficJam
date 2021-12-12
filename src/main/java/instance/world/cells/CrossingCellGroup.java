package instance.world.cells;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class CrossingCellGroup{

    private List<Cell> cellList;
    private boolean carIsCrossing;

    private Semaphore mutex;

    public CrossingCellGroup() {
        this.cellList = new ArrayList<>();
        this.mutex = new Semaphore(1);
        this.carIsCrossing = false;
    }

    public void enterThisCrossing() {
        if (!carIsCrossing) {
            try {
                mutex.acquire(1);
                carIsCrossing = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void exitThisCrossing() {
        this.carIsCrossing = false;
        mutex.release();
    }


    public List<Cell> getCellList() {
        return cellList;
    }
}
