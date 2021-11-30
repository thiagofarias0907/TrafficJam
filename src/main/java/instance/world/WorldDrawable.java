package instance.world;

import instance.world.cells.CellDrawing;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.List;

public class WorldDrawable extends JComponent {

    private float height;
    private float width;
    private float roadColWidth;
    private float roadLineWidth;
    List<CellDrawing> cellDrawingList;

    public WorldDrawable(float height, float width, float roadColWidth, float roadLineWidth, List<CellDrawing> cellDrawingList) {
        this.height = height;
        this.width = width;
        this.roadColWidth = roadColWidth;
        this.roadLineWidth = roadLineWidth;
        this.cellDrawingList = cellDrawingList;
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D graphics2D = configureSceneRender((Graphics2D) g);

        cellDrawingList.forEach( streetCell ->{
            streetCell.draw(graphics2D);
        });


    }

    private Graphics2D configureSceneRender(Graphics2D g) {
        Graphics2D graphics2D = g;

        RenderingHints renderingHints = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        graphics2D.setRenderingHints(renderingHints);

        Rectangle2D.Double background =  new Rectangle2D.Double(0,0,width,height);
        graphics2D.setColor(new Color(0,0, 0));
        graphics2D.fill(background);
        return graphics2D;
    }




}
