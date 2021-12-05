package instance.world.cells;

import instance.world.cars.Car;
import instance.world.cells.State.DangerZoneHandler;

public class Cell {

    private String id;
    private Direction direction;
    private Cell[] paths;
    private DangerZoneHandler dangerZoneHandler;
    private CellDrawing cellDrawing;
    private Car car;

    public Cell(String id, DangerZoneHandler dangerZoneHandler, CellDrawing cellDrawing) {
        this.id = id;
        this.dangerZoneHandler = dangerZoneHandler;
        this.cellDrawing = cellDrawing;
    }

    public Cell[] getPaths() {
        return paths;
    }

    public DangerZoneHandler getDangerZoneHandler() {
        return dangerZoneHandler;
    }

    public void setPaths(Cell[] paths){
        this.paths = paths;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public CellDrawing getCellDrawing() {
        return cellDrawing;
    }

    public String getId() {
        return id;
    }

    public Direction getDirection() {
        return direction;
    }

    public Car getCar() {
        return car;
    }

    public void enterThisRoad(Car car) {

        synchronized (this){
            System.out.println(car+" trying to enter ");

            if(this.car!=null){
                try {
                    System.out.println(car+" will be waiting");
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            final String[] valuesInKey = id.split("\s");

            System.out.println(car+" entering the road "+ this);

            car.getDrawing().setxPos(Integer.parseInt(valuesInKey[1]));
            car.getDrawing().setyPos(Integer.parseInt(valuesInKey[0]));
            this.car = car;

            System.out.println(" clearing the road "+ car.getCurrentRoad());

            if(car.getCurrentRoad()!=null){
                car.getCurrentRoad().exitThisRoad();
            }

            car.setCurrentRoad(this);

        }

    }


    private void exitThisRoad(){

        synchronized (this){
            this.car = null;
            notifyAll();
        }

    }

    public void setCar(Car car) {
        this.car = car;
    }
}
