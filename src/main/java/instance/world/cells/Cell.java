package instance.world.cells;

import instance.world.cells.State.DangerZoneHandler;

public class Cell {

    private String id;
    private Direction direction;
    private Cell[] paths;
    private DangerZoneHandler dangerZoneHandler;
    private CellDrawing cellDrawing;

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
}
