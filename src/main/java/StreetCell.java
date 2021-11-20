import DrawingPatterns.StreetCellDrawing;

public class StreetCell {

    private boolean hasCar;
    private boolean isBegin;
    private boolean isEnd;
    private StreetCellDrawing streetCellDrawing;

    public StreetCell(boolean hasCar, boolean isBegin, boolean isEnd, StreetCellDrawing streetCellDrawing) {
        this.hasCar = hasCar;
        this.isBegin = isBegin;
        this.isEnd = isEnd;
        this.streetCellDrawing = streetCellDrawing;
    }


    public boolean isHasCar() {
        return hasCar;
    }

    public boolean isBegin() {
        return isBegin;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setHasCar(boolean hasCar) {
        this.hasCar = hasCar;
    }


    public StreetCellDrawing getStreetCellDrawing() {
        return streetCellDrawing;
    }
}
