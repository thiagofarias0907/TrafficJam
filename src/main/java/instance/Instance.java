package instance;

import instance.strategy.*;
import instance.world.World;
import instance.world.WorldDrawable;
import instance.world.cells.Cell;
import instance.world.cells.CellDrawing;
import instance.world.cells.CrossingCell;
import instance.world.cells.Direction;
import instance.world.cells.State.DangerZoneHandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Instance {

    private float height;
    private float width;

    private int columnsCount;
    private int linesCount;

    private float roadColWidth;
    private float roadLineWidth;

    private World world;

    private DangerZoneHandler dangerZoneHandler;
    private List<String> fileLines;


    public Instance(int height, int width, DangerZoneHandler dangerZoneHandler, String path) {
        this.height = height;
        this.width = width;
        this.dangerZoneHandler = dangerZoneHandler;

        setFileLines(path);
        setLinesCount();
        setColumnsCount();
        formatFileLines();
        setRoadColWidth();
        setRoadLineWidth();
        makeWorld();

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

                CellDrawing cellDrawing = new CellDrawing(j,i, roadColWidth, roadLineWidth, Direction.NONE);
                Cell cell = new Cell(i+" "+j,dangerZoneHandler,cellDrawing);

                switch (lineElements[j]){
                    case "0":
                        cell.setDirection(Direction.NONE);
                        cell.getCellDrawing().setType(Direction.NONE);
                        break;

                    case "1" :
                        cell.setDirection(Direction.UP);
                        cell.getCellDrawing().setType(Direction.UP);
                        break;

                    case "2":
                        cell.setDirection(Direction.RIGHT);
                        cell.getCellDrawing().setType(Direction.RIGHT);
                        break;

                    case "3":
                        cell.setDirection(Direction.DOWN);
                        cell.getCellDrawing().setType(Direction.DOWN);
                    break;

                    case "4":
                        cell.setDirection(Direction.LEFT);
                        cell.getCellDrawing().setType(Direction.LEFT);
                    break;

                    default:
                        cell = new CrossingCell(i+" "+j,dangerZoneHandler,cellDrawing, Integer.parseInt(lineElements[i]));
                        cell.setDirection(Direction.UNDEF);
                        cell.getCellDrawing().setType(Direction.UNDEF);

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

                case UNDEF -> {
                    addPathsStrategy = new AddPathsUndefDirection();
                break;
                }

            }

            if(addPathsStrategy!=null){
                addPathsStrategy.addPaths(paths, grid, line, column, cell, linesCount, columnsCount);
            }
        }



    }

    private void makeWorld(){

        HashMap<String, Cell> grid = makeGrid();

        List<CellDrawing> cellDrawingList = new ArrayList<>();

        for(Cell cell : grid.values()){
            cellDrawingList.add(cell.getCellDrawing());
        }

        setCellPaths(grid);

        WorldDrawable worldDrawable = new WorldDrawable(height, width, roadColWidth, roadLineWidth, cellDrawingList);

        //todo: set list of cars
        world = new World(worldDrawable, grid, null);
    }


    public World getWorld() {
        return world;
    }
}
