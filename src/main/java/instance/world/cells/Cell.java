package instance.world.cells;

import instance.world.cars.Car;

public abstract class Cell {

    protected String id;
    protected Direction direction;
    protected Cell[] paths;
    protected CellDrawing cellDrawing;
    protected Car car;
    protected int value;

    public Cell(String id, CellDrawing cellDrawing) {
        this.id = id;
        this.cellDrawing = cellDrawing;
    }

    public Cell[] getPaths() {
        return paths;
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

    public abstract void enterThisRoad(Car car);

    public abstract void exitThisRoad();

    public void setCar(Car car) {
        this.car = car;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
