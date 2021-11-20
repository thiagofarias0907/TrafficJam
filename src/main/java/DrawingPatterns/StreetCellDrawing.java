package DrawingPatterns;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class StreetCellDrawing {

    private int xPos;
    private int yPos;
    private int width;
    private int height;
    private int type;
    private Color color;
    private boolean hasCar;
    private boolean isBegin;
    private boolean isEnd;

    public StreetCellDrawing(int xPos, int yPos, int width, int height, int type, Color color, boolean hasCar, boolean isBegin, boolean isEnd) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.type = type;
        this.color = color;
        this.hasCar = hasCar;
        this.isBegin = isBegin;
        this.isEnd = isEnd;
    }

    public void draw(Graphics2D graphics2D){
        Rectangle2D.Double cell = new Rectangle2D.Double(xPos,yPos,width,height);
        graphics2D.setColor(color);
        graphics2D.fill(cell);
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
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

    public int getType() {
        return type;
    }
}
