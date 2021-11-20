package DrawingPatterns;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class CarDrawing {

    private int xPos;
    private int yPos;
    private int scale;

    public CarDrawing(int xPos, int yPos, int scale) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.scale = scale;
    }

    public void draw(Graphics2D graphics2D){
        Rectangle2D.Double carBody = new Rectangle2D.Double(xPos+6,yPos + 10, scale,scale/2);
        Ellipse2D.Double wheel     = new Ellipse2D.Double(xPos + 8,yPos + 20,scale/4 ,scale/5);
        Ellipse2D.Double wheel2    = new Ellipse2D.Double(xPos + 18,yPos + 20,scale/4 ,scale/5);
        Ellipse2D.Double wheel3    = new Ellipse2D.Double(xPos + 8,yPos + 6 ,scale/4 ,scale/5);
        Ellipse2D.Double wheel4    = new Ellipse2D.Double(xPos + 18,yPos + 6 ,scale/4 ,scale/5);

        graphics2D.setColor(Color.RED);
        graphics2D.fill(carBody);

        graphics2D.setColor(Color.BLACK);
        graphics2D.fill(wheel);
        graphics2D.fill(wheel2);
        graphics2D.fill(wheel3);
        graphics2D.fill(wheel4);
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }
}
