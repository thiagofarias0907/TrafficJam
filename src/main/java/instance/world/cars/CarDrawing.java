package instance.world.cars;

import instance.Instance;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class CarDrawing {

    private float xPos = -1000;
    private float yPos = -1000;
    private float scale;
    private Color color;

    public CarDrawing(int scale, Color color) {
        this.scale = scale;
        this.color = color;
    }

    public void draw(Graphics2D graphics2D){
        Instance instance = Instance.getInstance();
        Rectangle2D.Double carBody = new Rectangle2D.Double(instance.getRoadColWidth()*xPos+ scale/5  ,instance.getRoadLineWidth()*yPos + scale/2    , scale     ,scale/2.1);
        Ellipse2D.Double wheel     = new Ellipse2D.Double(instance.getRoadColWidth()*xPos  + scale/4  ,instance.getRoadLineWidth()*yPos + scale/2.5  ,scale/4 ,scale/5);
        Ellipse2D.Double wheel2    = new Ellipse2D.Double(instance.getRoadColWidth()*xPos  + scale/1.2,instance.getRoadLineWidth()*yPos + scale/2.5  ,scale/4 ,scale/5);
        Ellipse2D.Double wheel3    = new Ellipse2D.Double(instance.getRoadColWidth()*xPos  + scale/4  ,instance.getRoadLineWidth()*yPos + scale/1.1  ,scale/4  ,scale/5);
        Ellipse2D.Double wheel4    = new Ellipse2D.Double(instance.getRoadColWidth()*xPos  + scale/1.2,instance.getRoadLineWidth()*yPos + scale/1.1  ,scale/4  ,scale/5);

        graphics2D.setColor(color);
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

    public void setScale(float scale) {
        this.scale = scale;
    }

    @Override
    public String toString() {
        return "CarDrawing{" +
                "xPos=" + xPos +
                ", yPos=" + yPos +
                ", color=" + color +
                '}';
    }
}
