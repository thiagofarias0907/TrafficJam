package instance.world.cars;

import instance.Instance;
import instance.world.cells.Cell;
import instance.world.cells.cellTypes.SynchronizedCell;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class CarInitializer implements Runnable {

    List<Car> cars;
    List<Cell> enterPoints;


    public CarInitializer(List<Car> cars, List<Cell> enterPoints) {
        this.cars = cars;
        this.enterPoints = enterPoints;
    }

    @Override
    public void run() {

        while (Instance.getInstance().isRunning()){

            int carsCount = Instance.getInstance().getWorld().getCarsCount();
            int carsQtd = Instance.getInstance().getCarsQuantity();


            if(carsCount < carsQtd){

                Random rand = new Random();

                int r = rand.nextInt(255);
                int g = rand.nextInt(255);
                int b = rand.nextInt(255);

                CarDrawing carDrawing = new CarDrawing( (int)(Instance.getInstance().getRoadColWidth()*0.8), new Color(r, g, b));
                Car car = new Car(carDrawing,Instance.getInstance().getMinVehiclesSpeedInMs(), Instance.getInstance().getMaxVehiclesSpeedInMs());

                int i =0;

                while (!enterPoints.get(i).enterThisRoad(car)) {

                    if(i+1>=enterPoints.size()){
                        i=0;
                    }
                    else {
                        i++;
                    }

                }

                Thread thread = new Thread(car);
                thread.start();
                Instance.getInstance().getWorld().getDrawable().addNewCarDrawing(carDrawing);



            }

        }

    }
}
