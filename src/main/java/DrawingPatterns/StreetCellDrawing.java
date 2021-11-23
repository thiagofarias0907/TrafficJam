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

    public StreetCellDrawing(int xPos, int yPos, int width, int height, int type, Color color) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.type = type;
        this.color = color;
    }

    public void draw(Graphics2D graphics2D){
        Rectangle2D.Double cell = new Rectangle2D.Double(xPos,yPos,width,height);
        graphics2D.setColor(color);
        graphics2D.fill(cell);
    }

    public int getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
