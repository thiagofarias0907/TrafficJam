package instance.world.cells;

import instance.world.cells.State.DangerZoneHandler;

public class CrossingCell extends Cell{


    private int value;

    public CrossingCell(String id, DangerZoneHandler dangerZoneHandler, CellDrawing cellDrawing, int value) {
        super(id, dangerZoneHandler, cellDrawing);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
