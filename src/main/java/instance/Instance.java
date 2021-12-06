package instance;

import Renderer.Renderer;
import instance.strategy.*;
import instance.world.World;
import instance.world.WorldDrawable;
import instance.world.cars.Car;
import instance.world.cars.CarDrawing;
import instance.world.cells.Cell;
import instance.world.cells.CellDrawing;
import instance.world.cells.Direction;
import instance.world.cells.cellTypes.CellTypes;
import instance.world.cells.cellTypes.SemaphoreCell;
import instance.world.cells.cellTypes.SynchronizedCell;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;


/*
* This class is responsible for creating and having the instance of the world.
* It can be optimized, but there is no need to do it right now because it is not being executed in real time,
* and the timing for accomplishing its requirements are acceptable.
* */

public class Instance {

    private static Instance instance;

    private float height;
    private float width;

    private int columnsCount;
    private int linesCount;

    private float roadColWidth;
    private float roadLineWidth;

    private int carsQuantity;
    private long minVehiclesSpeedInMs;
    private long maxVehiclesSpeedInMs;

    CellTypes cellTypes;
    private World world;

    private List<String> fileLines;


    public Instance(int height, int width, CellTypes cellTypes, String path, int carsQuantity, long minVehiclesSpeedInMs, long maxVehiclesSpeedInMs) {
        this.height = height;
        this.width = width;
        this.carsQuantity = carsQuantity;
        this.minVehiclesSpeedInMs = minVehiclesSpeedInMs;
        this.maxVehiclesSpeedInMs = maxVehiclesSpeedInMs;
        this.cellTypes = cellTypes;

        setFileLines(path);
        setLinesCount();
        setColumnsCount();
        formatFileLines();
        setRoadColWidth();
        setRoadLineWidth();
        makeWorld();

        instance = this;
    }

    private void setFileLines(String path) {
        Path filePath = Paths.get(path);
        try {
            fileLines = Files.readAllLines(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setLinesCount(){
        linesCount = Integer.parseInt(fileLines.get(0));
    }

    private void setColumnsCount(){
        columnsCount = Integer.parseInt(fileLines.get(1));
    }

    private void formatFileLines(){
        fileLines.remove(0);
        fileLines.remove(0);
    }



    private void setRoadColWidth(){
        roadColWidth = height/ columnsCount;
    }

    private void setRoadLineWidth(){
        roadLineWidth = width/ linesCount;
    }

    private HashMap<String, Cell> makeGrid(){

        HashMap<String, Cell> grid = new HashMap<>();

        for(int i = 0; i<fileLines.size(); i++){

            final String[] lineElements = fileLines.get(i).split("\t");

            for(int j = 0; j<lineElements.length; j++){

                Cell cell = null;
                CellDrawing cellDrawing = new CellDrawing(j,i, roadColWidth, roadLineWidth, Direction.NONE);

                if(cellTypes == CellTypes.SYNCHRONIZED){
                    cell = new SynchronizedCell(i+" "+j, cellDrawing);
                }
                else if(cellTypes== CellTypes.SEMAPHORE){
                    cell = new SemaphoreCell(i+" "+j, cellDrawing);
                }




                switch (lineElements[j]){
                    case "0":
                        cell.setDirection(Direction.NONE);
                        cell.setValue(0);
                        cell.getCellDrawing().setType(Direction.NONE);
                        break;

                    case "1" :
                        cell.setDirection(Direction.UP);
                        cell.setValue(1);
                        cell.getCellDrawing().setType(Direction.UP);
                        break;

                    case "2":
                        cell.setDirection(Direction.RIGHT);
                        cell.setValue(2);
                        cell.getCellDrawing().setType(Direction.RIGHT);
                        break;

                    case "3":
                        cell.setDirection(Direction.DOWN);
                        cell.setValue(3);
                        cell.getCellDrawing().setType(Direction.DOWN);
                    break;

                    case "4":
                        cell.setDirection(Direction.LEFT);
                        cell.setValue(4);
                        cell.getCellDrawing().setType(Direction.LEFT);
                    break;

                    default:
                        cell.setValue(Integer.parseInt(lineElements[j]));
                        cell.setDirection(Direction.CROSSING);
                        cell.getCellDrawing().setType(Direction.CROSSING);

                }

                grid.put(i+" "+j, cell);
            }

        }

        return grid;
    }


    private void setCellPaths(HashMap<String,Cell> grid){

        for (Cell cell : grid.values()) {

            String key = cell.getId();
            final String[] valuesInKey = key.split("\s");
            final int line = Integer.parseInt(valuesInKey[0]);
            final int column = Integer.parseInt(valuesInKey[1]);


            AddPathsStrategy addPathsStrategy= null;
            ArrayList<Cell> paths = new ArrayList<>();

            switch (cell.getDirection()){
                case UP -> {
                addPathsStrategy = new AddPathsUpDirection();
                break;
                }

                case DOWN -> {
                addPathsStrategy = new AddPathsDownDirection();
                break;
                }

                case LEFT -> {
                    addPathsStrategy = new AddPathsLeftDirection();
                break;
                }

                case RIGHT -> {
                    addPathsStrategy = new AddPathsRightDirection();
                break;
                }

                case CROSSING -> {
                    addPathsStrategy = new AddPathsUndefDirection();
                break;
                }

            }

            if(addPathsStrategy!=null){
                addPathsStrategy.addPaths(paths, grid, line, column, cell);
            }
        }

    }

    private List<Cell> getEnterCells(HashMap<String, Cell> grid)
    {
        HashSet<Cell> cellList = new HashSet<>();

        for(int i=0; i<columnsCount-1;i++){

            Cell cell = grid.get(0+" "+i);

            if(cell!=null && cell.getDirection()==Direction.DOWN){
                cellList.add(cell);
            }

            cell = grid.get(linesCount-1+" "+i);

            if(cell!=null && cell.getDirection()==Direction.UP){
                cellList.add(cell);
            }

        }


        for(int i=0; i<linesCount-1;i++){

            Cell cell = grid.get(i+" "+0);

            if(cell!=null && cell.getDirection()==Direction.RIGHT){
                cellList.add(cell);
            }

            cell = grid.get(i+" "+(columnsCount-1));

            if(cell!=null  && cell.getDirection()==Direction.LEFT){
                cellList.add(cell);
            }
        }


        return cellList.stream().toList();
    }

    private  List<Cell>  getExitCells(HashMap<String, Cell> grid){

        HashSet<Cell> cellList = new HashSet<>();

        for(int i=0; i<columnsCount-1;i++){

            Cell cell = grid.get(0+" "+i);

            if(cell!=null && cell.getDirection()==Direction.UP){
                cellList.add(cell);
            }

            cell = grid.get(linesCount-1+" "+i);

            if(cell!=null && cell.getDirection()==Direction.DOWN){
                cellList.add(cell);
            }

        }


        for(int i=0; i<linesCount-1;i++){

            Cell cell = grid.get(i+" "+0);

            if(cell!=null && cell.getDirection()==Direction.LEFT){
                cellList.add(cell);
            }

            cell = grid.get(i+" "+(columnsCount-1));

            if(cell!=null  && cell.getDirection()==Direction.RIGHT){
                cellList.add(cell);
            }
        }

        return cellList.stream().toList();
    }


    private LinkedList<Car> makeVehicles(){
        LinkedList<Car> cars = new LinkedList<>();
        for(int i=0;i<carsQuantity;i++){
            Random rand = new Random();

            int r = rand.nextInt(255);
            int g = rand.nextInt(255);
            int b = rand.nextInt(255);

            CarDrawing carDrawing = new CarDrawing( 25, new Color(r, g, b));

        Car car = new Car(carDrawing,minVehiclesSpeedInMs, maxVehiclesSpeedInMs);
        cars.add(car);
        }
        return cars;
    }

    private void makeWorld(){

        HashMap<String, Cell> grid = makeGrid();
        LinkedList<Car> cars = makeVehicles();

        List<CellDrawing> cellDrawingList = new ArrayList<>();
        List<CarDrawing> carDrawingList = new ArrayList<>();

        for(Cell cell : grid.values()){
            cellDrawingList.add(cell.getCellDrawing());
        }

        for (Car car: cars) {
            carDrawingList.add(car.getDrawing());
        }

        setCellPaths(grid);
        WorldDrawable worldDrawable = new WorldDrawable(height, width, roadColWidth, roadLineWidth, cellDrawingList, carDrawingList);

        world = new World(worldDrawable, grid, cars, getEnterCells(grid), getExitCells(grid));

        Thread thread = new Thread(new Renderer(), "Renderer");
        thread.start();
    }

    public static Instance getInstance(){
        return instance;
    }

    public float getRoadColWidth() {
        return roadColWidth;
    }

    public float getRoadLineWidth() {
        return roadLineWidth;
    }

    public World getWorld() {
        return world;
    }
}
