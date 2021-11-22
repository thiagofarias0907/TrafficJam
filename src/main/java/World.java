import DrawingPatterns.StreetCellDrawing;
import Enums.StreetCellEnum;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultEdgeFunction;
import org.jgrapht.graph.SimpleDirectedGraph;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class World extends JComponent {

    private int width;
    private int height;
    private static int matrixSize = 20;
    private static int roadColWidth;
    private static int roadLineWidth;
    private static List<StreetCell> streetCellList = new ArrayList();
    private static Set<CarThread> carsList = new HashSet();


    HashMap<Integer,Graph> graphHashMap = new HashMap<>();

    private int columns;
    private int lines;

    private Graphics2D graphics2D;


    public World(int width, int height) {
        this.width = width;
        this.height = height;
        roadColWidth = height/matrixSize;
        roadLineWidth = width/matrixSize;
        //read a file and draw street map
        readMapFile();
        //add first cars
        if (carsList.isEmpty())
            addCars();

        Thread worldThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    repaint();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        worldThread.start();
    }

    /**
     * Overrides the JComponent method, painting the intended drawings
     * @param graphics
     */
    @Override
    protected void paintComponent(Graphics graphics){
        Graphics2D graphics2D = (Graphics2D) graphics;

        RenderingHints renderingHints = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );
        graphics2D.setRenderingHints(renderingHints);

        Rectangle2D.Double background =  new Rectangle2D.Double(0,0,width,height);
        graphics2D.setColor(new Color(39,120, 12));
        graphics2D.fill(background);

        for (int i = 0; i < height; i = i+roadLineWidth) {
            Line2D.Double lines = new Line2D.Double(0,i,width,i);
            graphics2D.setColor(new Color(200,200,200));
            graphics2D.draw(lines);
        }

        for (int i = 0; i < width; i = i+roadColWidth) {
            Line2D.Double cols = new Line2D.Double(i,0,i,height);
            graphics2D.setColor(new Color(200,200,200));
            graphics2D.draw(cols);
        }

        this.graphics2D = graphics2D;
        //draw street and cars
        streetCellList.forEach( streetCell ->{
            streetCell.getStreetCellDrawing().draw(graphics2D);
        });
        carsList.forEach(car ->{
            car.setGraphics2D(graphics2D);
            car.getCarDrawing().draw(graphics2D);
        });

    }

    /**
     * Read a source map file
     */
    private void readMapFile()  {
        streetCellList = new ArrayList<StreetCell>();
        String file ="C:\\Users\\Thiago\\Desktop\\Malhas de Exemplo-20211110\\malha1-caso1.txt";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));

            lines = Integer.parseInt(reader.readLine());
            columns  = Integer.parseInt(reader.readLine());

            String[][] map = new String[lines][columns];

            String line = "";
            for (int i = 0; i < lines; i++) {
                line = reader.readLine();
                map[i] = line.split("\t");
            }
            reader.close();

            System.out.println(map.length);
            System.out.println(map[0].length);
            for (int i = 0; i < map.length ; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    boolean isBegin = false;
                    boolean isEnd   = false;

                    if(i == 0) {
                        if (map[i][j].equals(StreetCellEnum.BAIXO.getValue()))
                            isBegin = true;
                        if (map[i][j].equals(StreetCellEnum.CIMA.getValue()))
                            isEnd = true;
                    } else if (i == lines-1) {
                        if (map[i][j].equals(StreetCellEnum.BAIXO.getValue()))
                            isEnd = true;
                        if (map[i][j].equals(StreetCellEnum.CIMA.getValue()))
                            isBegin = true;
                    } else if (j == 0) {
                        if (map[i][j].equals(StreetCellEnum.ESQUERDA.getValue()))
                            isEnd = true;
                        if (map[i][j].equals(StreetCellEnum.DIREITA.getValue()))
                            isBegin = true;
                    } else if (j == columns-1) {
                        if (map[i][j].equals(StreetCellEnum.DIREITA.getValue()))
                            isEnd = true;
                        if (map[i][j].equals(StreetCellEnum.ESQUERDA.getValue()))
                            isBegin = true;
                    }

                    // Create a object streetCell in order to draw it on screen
                    StreetCellDrawing streetCellDrawing = new StreetCellDrawing(
                            j*roadColWidth,
                            i*roadLineWidth,
                            roadColWidth,
                            roadLineWidth,
                            Integer.parseInt(map[i][j]),
                            getColor(map[i][j])
                    );
                    StreetCell streetCell = new StreetCell(j*roadColWidth,i*roadLineWidth,false,isBegin,isEnd,Integer.parseInt(map[i][j]),streetCellDrawing);
                    streetCellList.add(streetCell);

//                    if (Integer.parseInt(map[i][j]) != 0)
//                        g.addVertex(streetCell);

                }
                String l = i + " - " ;
                for (String s : map[i]) {
                    l += s;
                }
                System.out.println(l);
            }

            //create routes as graphs
            makeGraphs();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Color getColor(String value){
        for (StreetCellEnum cellEnum : StreetCellEnum.values()) {
            if (value.equals(cellEnum.getValue()))
                return cellEnum.getColor();
        }
        return new Color(0,0,0);
    }

    public void addCars(){
        streetCellList.forEach(streetCell ->{
            CarThread carThread = new CarThread(streetCell,streetCell.getxPos(), streetCell.getyPos(),streetCell.getType());
            if (streetCell.isBegin() && !streetCell.hasCar()) {
                carsList.add(carThread);
                carThread.setPathGraph(graphHashMap.get(streetCellList.indexOf(streetCell)));
                streetCell.setHasCar(true);
            }
        });
    }

    public CarThread newCar(){
        for (StreetCell streetCell : streetCellList) {
            if (streetCell.isBegin() && !streetCell.hasCar()) {
                streetCell.setHasCar(true);
                CarThread carThread =  new CarThread(streetCell,streetCell.getxPos(), streetCell.getyPos(),streetCell.getType());
                carThread.setPathGraph(graphHashMap.get(streetCellList.indexOf(streetCell)));
                return carThread;
            }
        }
        return null;
    }

    public void moveCars(){

        for (CarThread carThread : carsList) {
            if(carThread.getStreetCell().isEnd()) {
                CarThread newCar = newCar();
                carThread.setDirection(newCar.getDirection());
                carThread.setPosX(newCar.getPosX());
                carThread.setPosY(newCar.getPosX());
                carThread.setCarDrawing(newCar.getCarDrawing());
                carThread.setStreetCell(newCar.getStreetCell());
                carThread.setPathGraph(newCar.gePathGraph());
                continue;
            }
            carThread.setGraphics2D(this.graphics2D);
            carThread.run();
            Thread thread = new Thread(carThread);
            thread.start();
        }
    }

//    public boolean outOfBounds(CarThread carThread){
//        if (carThread.getPosX() < 0 || carThread.getPosX() > width)
//            return true;
//        if (carThread.getPosY() < 0 || carThread.getPosY() > height)
//            return true;
//        return false;
//    }

    public StreetCell findStreetCell(int xPos, int yPos){
        for(StreetCell streetCell : streetCellList){
            if (streetCell.getxPos() == xPos && streetCell.getyPos()== yPos) {
                return streetCell;
            }
        }
        return null;
    }

    private void makeGraphs(){
        this.graphHashMap = new HashMap<>();
        Graph<StreetCell,DefaultEdge> g;
        for (int i = 0; i < streetCellList.size(); i++) {
            StreetCell currentCell = streetCellList.get(i);

            //skip empty ones
            if (currentCell.getType() == 0)
                continue;

//            //skip what is not on the borders
//            if ( (i % (columns-1) != 0) && (i > columns) && (i % (columns) != 0) && i < streetCellList.size()-columns)
//                continue;

            // initiate the graph
            if (currentCell.isBegin()) {
                g = new SimpleDirectedGraph<>(DefaultEdge.class);
                g.addVertex(currentCell);
                graphHashMap.put(i,g);
            }
        }

        graphHashMap.forEach((key, value)->{
            System.out.println(key + " - " + value.toString());
            setNextEdge(key,value);
        });

        System.out.println(graphHashMap.size());
        System.out.println(graphHashMap.get(columns/2-1));
        System.out.println(graphHashMap.get(columns*lines/2));
        System.out.println(graphHashMap.get(columns*lines/2-1));
        System.out.println(graphHashMap.get(streetCellList.size()-(columns/2)));

    }

    private int setNextEdge(int i, Graph g){
        StreetCell currentCell;

        if (i >= 0)
            currentCell = streetCellList.get(i);
        else
            return -1;

        if (currentCell.isEnd())
            return -1;

        int nextCellIndex = 0;
        int nextCellAltIndex = 0;
        StreetCell nextCell = null;
        StreetCell nextCellAlt = null;
        if (currentCell.getType() == Integer.parseInt(StreetCellEnum.DIREITA.getValue())){
            nextCellIndex = i+1;
            nextCell = streetCellList.get(nextCellIndex);
        } else if (currentCell.getType() == Integer.parseInt(StreetCellEnum.ESQUERDA.getValue())){
            nextCellIndex = i-1;
            nextCell = streetCellList.get(nextCellIndex);
        } else if (currentCell.getType() == Integer.parseInt(StreetCellEnum.CIMA.getValue())
                || currentCell.getType() == Integer.parseInt(StreetCellEnum.CRUZA_CIMA.getValue())){
            nextCellIndex = i-columns;
            nextCell = streetCellList.get(nextCellIndex);
        } else if (currentCell.getType() == Integer.parseInt(StreetCellEnum.BAIXO.getValue())
                || currentCell.getType() == Integer.parseInt(StreetCellEnum.CRUZA_BAIXO.getValue())){
            nextCellIndex = i+columns;
            nextCell = streetCellList.get(nextCellIndex);
        }else if (currentCell.getType() == Integer.parseInt(StreetCellEnum.CRUZA_BAIXO_ESQUERDA.getValue())){
            nextCellIndex = i+columns;
            nextCellAltIndex = i-1;
            nextCell    = streetCellList.get(nextCellIndex);
            nextCellAlt = streetCellList.get(nextCellAltIndex);
        } else if (currentCell.getType() == Integer.parseInt(StreetCellEnum.CRUZA_BAIXO_DIREITA.getValue())){
            nextCellIndex = i+columns;
            nextCellAltIndex = i+1;
            nextCell    = streetCellList.get(nextCellIndex);
            nextCellAlt = streetCellList.get(nextCellAltIndex);
        }else if (currentCell.getType() == Integer.parseInt(StreetCellEnum.CRUZA_CIMA_ESQUERDA.getValue())){
            nextCellIndex = i-columns;
            nextCellAltIndex = i-1;
            nextCell    = streetCellList.get(nextCellIndex);
            nextCellAlt = streetCellList.get(nextCellAltIndex);
        } else if (currentCell.getType() == Integer.parseInt(StreetCellEnum.CRUZA_CIMA_DIREITA.getValue())){
            nextCellIndex = i-columns;
            nextCellAltIndex = i+1;
            nextCell    = streetCellList.get(nextCellIndex);
            nextCellAlt = streetCellList.get(nextCellAltIndex);
        }

        if (nextCell == null && nextCellAlt == null)
            return -1;

        if (nextCell != null) {
            g.addVertex(nextCell);
            g.addEdge(currentCell, nextCell);
            if (!nextCell.isEnd())
                return setNextEdge(setNextEdge(nextCellIndex,g),g);
            else
                return -1;
        }
        if (nextCellAlt != null) {
            g.addVertex(nextCellAlt);
            g.addEdge(currentCell, nextCellAlt);
            if (!nextCellAlt.isEnd())
                return setNextEdge(setNextEdge(nextCellAltIndex,g),g);
            else
                return -1;
        }

        return -1;
    }

}
