package instance.world.cars;

import instance.world.cells.Cell;
import instance.world.cells.cellTypes.SynchronizedCell;

import java.util.List;
import java.util.Random;

public class CarInitializer implements Runnable {

    List<Car> cars;
    List<Cell> enterPoints;

    public CarInitializer(List<Car> cars, List<Cell> enterPoints) {
        this.cars = cars;
        this.enterPoints = enterPoints;

    }


    public void initialize(){

        if(enterPoints.get(0) instanceof SynchronizedCell){

            for (Car car: this.cars) {
                Random rand = new Random();

                Thread thread = new Thread(car);
                thread.start();

                enterPoints.get(rand.nextInt(enterPoints.size())).enterThisRoad(car);

            }
        }
        else {
            Thread thread = new Thread(this);
            thread.start();
        }

    }

    @Override
    public void run() {

        int i =0;
        for (Car car: cars) { //para cada carro

            while (!enterPoints.get(i).enterThisRoad(car)) {

                try {
                    Thread.sleep(car.getSpeedInMs());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } //fique tentando colocar o carro na rua dele
            Thread thread = new Thread(car); //quando conseguir inicialize a thread do carro
            thread.start();

            if(i+1>=enterPoints.size()){
                i=0;
            }
            else {
                i++;
            }
        }

    }
}
