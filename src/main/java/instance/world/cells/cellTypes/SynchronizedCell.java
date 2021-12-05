package instance.world.cells.cellTypes;

import instance.world.cars.Car;
import instance.world.cells.Cell;
import instance.world.cells.CellDrawing;

public class SynchronizedCell extends Cell {


    public SynchronizedCell(String id, CellDrawing cellDrawing) {
        super(id, cellDrawing);
    }

    @Override
    public void enterThisRoad(Car car) {

        synchronized (this){

            if(this.car==null){
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
    }

    @Override
    public void exitThisRoad() {

        synchronized (this){
            this.car = null;
        }

    }
}
