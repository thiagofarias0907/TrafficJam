import DrawingPatterns.CarDrawing;
import Enums.StreetCellEnum;

import java.awt.*;

public class CarThread implements Runnable {

    private CarDrawing carDrawing;
    private StreetCell streetCell;
    private int posX;
    private int posY;
    private int direction;
    private Graphics2D graphics2D;

    public CarThread(StreetCell streetCell, int posX, int posY, int direction) {
        this.carDrawing = new CarDrawing(posX,posY,20);
        this.streetCell = streetCell;
        this.posX = posX;
        this.posY = posY;
        this.direction = direction;
    }

    @Override
    public void run() {

            synchronized (Thread.currentThread()) {
                try {
                    Thread.currentThread().wait(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        if ((posX >= 0 && posX <= 800) && (posY >= 0 && posY <= 800)) {
            if (this.streetCell.getStreetCellDrawing().getType() == Integer.parseInt(StreetCellEnum.BAIXO.getValue()))
                this.posY += 30;
            if (this.streetCell.getStreetCellDrawing().getType() == Integer.parseInt(StreetCellEnum.CIMA.getValue()))
                this.posY -= 30;
            if (this.streetCell.getStreetCellDrawing().getType() == Integer.parseInt(StreetCellEnum.ESQUERDA.getValue()))
                this.posX -= 30;
            if (this.streetCell.getStreetCellDrawing().getType() == Integer.parseInt(StreetCellEnum.DIREITA.getValue()))
                this.posX += 30;
            System.out.println("desenho carro " + posX + ',' + posY);
            this.carDrawing = new CarDrawing(posX, posY, 20);
            this.carDrawing.draw(this.graphics2D);
        }


    }

    public CarDrawing getCarDrawing() {
        return carDrawing;
    }

    public StreetCell getStreetCell() {
        return streetCell;
    }

    public int getDirection() {
        return direction;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setGraphics2D(Graphics2D graphics2D){
        this.graphics2D = graphics2D;
    }

    public void setCarDrawing(CarDrawing carDrawing) {
        this.carDrawing = carDrawing;
    }

    public void setStreetCell(StreetCell streetCell) {
        this.streetCell = streetCell;
    }

}
