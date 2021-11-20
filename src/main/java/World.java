import Enums.StreetCellEnum;
import DrawingPatterns.StreetCellDrawing;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

public class World extends JComponent {

    private int width;
    private int height;
    private static int matrixSize = 20;
    private static int roadColWidth;
    private static int roadLineWidth;
    private static List<StreetCell> streetCellList = new ArrayList();
    private static Set<CarThread> carsList = new HashSet();

    private Graphics2D graphics2D;

    public World(int width, int height) {
        this.width = width;
        this.height = height;
        roadColWidth = height/matrixSize;
        roadLineWidth = width/matrixSize;
        //read a file and draw street map
        readMapFile(graphics2D);
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
            ((CarThread) car).setGraphics2D(graphics2D);
            ((CarThread) car).getCarDrawing().draw(graphics2D);
        });

    }

    /**
     * Read a source map file and draw it's streets 'cells' / parts
     * @param graphics2D
     */
    private void readMapFile(Graphics2D graphics2D)  {
        streetCellList = new ArrayList<StreetCell>();
        String file ="C:\\Users\\Thiago\\Desktop\\Malhas de Exemplo-20211110\\malha1-caso1.txt";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));

            int lines = Integer.parseInt(reader.readLine());
            int columns  = Integer.parseInt(reader.readLine());

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
                            ,false
                            ,isBegin
                            ,isEnd
                    );
                    StreetCell streetCell = new StreetCell(false,isBegin,isEnd,streetCellDrawing);
                    streetCellList.add(streetCell);
                }
                String l = i + " - " ;
                for (String s : map[i]) {
                    l += s;
                }
                System.out.println(l);
            }

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
            StreetCellDrawing streetCellDrawing = streetCell.getStreetCellDrawing();
            CarThread carThread = new CarThread(streetCell,streetCellDrawing.getxPos(), streetCellDrawing.getyPos(),streetCellDrawing.getType());
            if (streetCellDrawing.isBegin() && !streetCellDrawing.isHasCar()) {
                carsList.add(carThread);
                streetCell.setHasCar(true);
            }
        });
    }

    public CarThread newCar(){
        for (StreetCell streetCell : streetCellList) {
            StreetCellDrawing streetCellDrawing = streetCell.getStreetCellDrawing();
            if (streetCellDrawing.isBegin() && !streetCellDrawing.isHasCar()) {
                return new CarThread(streetCell,streetCellDrawing.getxPos(), streetCellDrawing.getyPos(),streetCellDrawing.getType());
            }
        }
        return null;
    }

    public void moveCars(){

        for (CarThread carThread : carsList) {
            if(outOfBounds(carThread)) {
                CarThread newCar = newCar();
                carThread.setDirection(newCar.getDirection());
                carThread.setPosX(newCar.getPosX());
                carThread.setPosY(newCar.getPosX());
                carThread.setCarDrawing(newCar.getCarDrawing());
                carThread.setStreetCell(newCar.getStreetCell());
                continue;
            }
            carThread.setGraphics2D(this.graphics2D);
            carThread.run();
            Thread thread = new Thread(carThread);
            thread.start();
        }
    }

    public boolean outOfBounds(CarThread carThread){
        if (carThread.getPosX() < 0 || carThread.getPosX() > width)
            return true;
        if (carThread.getPosY() < 0 || carThread.getPosY() > height)
            return true;
        return false;
    }
}
