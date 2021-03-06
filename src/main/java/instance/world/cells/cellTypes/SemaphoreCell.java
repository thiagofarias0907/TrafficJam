package instance.world.cells.cellTypes;

import instance.world.cars.Car;
import instance.world.cells.Cell;
import instance.world.cells.CellDrawing;

import java.time.LocalDateTime;
import java.util.concurrent.Semaphore;

public class SemaphoreCell extends Cell {

    private Semaphore mutex;

    public SemaphoreCell(String id, CellDrawing cellDrawing) {
        super(id, cellDrawing);
        mutex = new Semaphore(1);
    }

    @Override
    public boolean enterThisRoad(Car car) {

            if(this.car==null){

                try {
                    mutex.acquire();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                final String[] valuesInKey = id.split("\s");


                car.getDrawing().setxPos(Integer.parseInt(valuesInKey[1]));
                car.getDrawing().setyPos(Integer.parseInt(valuesInKey[0]));
                this.car = car;


                System.out.println("Célula " + this + "; Carro " + car + "; Entrada; " + LocalDateTime.now());
                if(car.getCurrentRoad()!=null){
                    car.getCurrentRoad().exitThisRoad();
                }

                car.setCurrentRoad(this);

                return true;
            }
            else {
                return false;
            }

    }

    @Override
    public void exitThisRoad() {

        System.out.println("Célula " + this + "; Carro " + car.toString() + "; Saída; " + LocalDateTime.now());
        this.car = null;
        mutex.release();
    }
}
