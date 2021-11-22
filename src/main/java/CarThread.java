import DrawingPatterns.CarDrawing;
import Enums.StreetCellEnum;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.awt.*;
import java.util.Random;
import java.util.Set;

public class CarThread implements Runnable {

    private CarDrawing carDrawing;
    private StreetCell streetCell;
    private int posX;
    private int posY;
    private int direction;
    private Graphics2D graphics2D;
    private Color color;

    private Graph<StreetCell,DefaultEdge> pathGraph;

    public CarThread(StreetCell streetCell, int posX, int posY, int direction) {
        Random random = new Random();
        int red   = random.nextInt(255);
        int green = random.nextInt(255);
        int blue  = random.nextInt(255);
        this.color = new Color(red,green,blue);
        this.carDrawing = new CarDrawing(posX,posY,20,color);

        this.streetCell = streetCell;
        this.posX = posX;
        this.posY = posY;
        this.direction = direction;
    }

    @Override
    public void run() {

        synchronized (Thread.currentThread()) {
            try {
                Thread.currentThread().wait(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        StreetCell vertexCell  = this.pathGraph.vertexSet().stream().filter((vertex) -> vertex.equals(this.streetCell)).findAny().get();
        Set<DefaultEdge> edges = this.pathGraph.edgesOf(vertexCell);
        StreetCell destinationCell = this.pathGraph.getEdgeTarget((DefaultEdge) edges.toArray()[edges.toArray().length-1]);
        if (!destinationCell.hasCar()) {
            this.posX = destinationCell.getxPos();
            this.posY = destinationCell.getyPos();
            this.streetCell.setHasCar(false);
            this.streetCell = destinationCell;
            this.streetCell.setHasCar(true);
            this.carDrawing.setxPos(posX);
            this.carDrawing.setyPos(posY);
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

    public void setPathGraph(Graph pathGraph) {
        this.pathGraph = pathGraph;
    }

    public Graph gePathGraph() {
        return pathGraph;
    }
}
