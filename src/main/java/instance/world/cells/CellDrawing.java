package instance.world.cells;

import instance.world.cells.Direction;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class CellDrawing {

    private float xPos;
    private float yPos;
    private float width;
    private float height;
    private Color color;

    private final float[] gap = new float[]{.5f,.5f};

    public CellDrawing(float xPos, float yPos, float width, float height, Direction type) {
        this.xPos = xPos * width;
        this.yPos = yPos * height;
        this.width = width-gap[0];
        this.height = height-gap[1];
        setType(type);
    }

    public void draw(Graphics2D graphics2D){
        Rectangle2D.Double cell = new Rectangle2D.Double(xPos,yPos,width,height);
        graphics2D.setColor(color);
        graphics2D.fill(cell);
    }

    public void setType(Direction type) {
        switch (type){
            case UP -> color = new Color(100,100,200) ;
            case DOWN -> color = new Color(100,100,100);
            case LEFT -> color =  new Color(200,100,100);
            case RIGHT -> color = new Color(100,200,100);
            case NONE -> color = new Color(200,200,200);
            case UNDEF -> color = new Color(180,180,180);
        }
    }
}
