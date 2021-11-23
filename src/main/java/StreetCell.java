import DrawingPatterns.StreetCellDrawing;

import java.util.Objects;
import java.util.concurrent.Semaphore;

public class StreetCell {

    private int xPos;
    private int yPos;
    private boolean hasCar;
    private boolean isBegin;
    private boolean isEnd;
    private int type;
    private StreetCellDrawing streetCellDrawing;

    private Semaphore mutex = new Semaphore(1);

    public StreetCell(int xPos, int yPos, boolean hasCar, boolean isBegin, boolean isEnd, int type, StreetCellDrawing streetCellDrawing) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.hasCar = hasCar;
        this.isBegin = isBegin;
        this.isEnd = isEnd;
        this.type = type;
        this.streetCellDrawing = streetCellDrawing;
    }

    public synchronized boolean hasCar() {
        return hasCar;
    }

    public boolean isBegin() {
        return isBegin;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setHasCar(boolean hasCar) {
        try {
            mutex.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.hasCar = hasCar;
        mutex.release();
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public int getType() {
        return type;
    }

    public StreetCellDrawing getStreetCellDrawing() {
        return streetCellDrawing;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StreetCell that = (StreetCell) o;
        return xPos == that.xPos &&
                yPos == that.yPos &&
                isBegin == that.isBegin &&
                isEnd == that.isEnd &&
                type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xPos, yPos, isBegin, isEnd, type);
    }
}
