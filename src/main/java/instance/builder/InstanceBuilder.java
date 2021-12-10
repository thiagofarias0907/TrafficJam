package instance.builder;


/* use this class to integrate with the buttons */

import instance.Instance;
import instance.world.cells.cellTypes.CellTypes;


public class InstanceBuilder {

    private CellTypes cellTypes;
    private  String path;
    private int carsQuantity;
    private long minVehicleSpeedInMs;
    private long maxVehicleSpeedInMs;

    public void setCellTypes(CellTypes cellTypes) {
        this.cellTypes = cellTypes;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setCarsQuantity(int carsQuantity) {
        this.carsQuantity = carsQuantity;
    }

    public void setMinVehicleSpeedInMs(long minVehicleSpeedInMs) {
        this.minVehicleSpeedInMs = minVehicleSpeedInMs;
    }

    public void setMaxVehicleSpeedInMs(long maxVehicleSpeedInMs) {
        this.maxVehicleSpeedInMs = maxVehicleSpeedInMs;
    }

    public void build(){
        Instance instance = new Instance(600, 600, cellTypes, path, carsQuantity, minVehicleSpeedInMs,maxVehicleSpeedInMs);
    }


}
