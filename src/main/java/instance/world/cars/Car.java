package instance.world.cars;

import instance.world.cells.Cell;

public class Car implements Runnable{

    private Cell currentRoad;
    private float speed;

    public boolean moveToTheNextRoad(){
        //todo: random the index of this path, respecting the limits of this array;
        Cell cell = currentRoad.getPaths()[0];


        if(cell.getDangerZoneHandler().isAvailable()){
            cell.getDangerZoneHandler().enterTheDangerZone();
            currentRoad.getDangerZoneHandler().exitedDangerZone();
            currentRoad = cell;
        }

        return false;

    }

    @Override
    public void run() {

    }
}
