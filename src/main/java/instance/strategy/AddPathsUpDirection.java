package instance.strategy;

import instance.world.cells.Cell;
import instance.world.cells.Direction;

import java.util.ArrayList;
import java.util.HashMap;

public class AddPathsUpDirection implements AddPathsStrategy {

    @Override
    public void addPaths(ArrayList<Cell> paths, HashMap<String, Cell> grid, int line, int column, Cell cell, int lineCount, int RowCount) {


        Cell upCell = grid.get((line-1)+" "+column);
        Cell leftCell = grid.get(line +" "+ (column-1));
        Cell rightCell = grid.get(line +" "+ (column+1)) ;
        Cell upLeftCell = grid.get((line-1) +" "+ (column-1));
        Cell upRightCell =grid.get((line-1) +" "+ (column+1));



            if(rightCell!= null && (rightCell.getDirection()== Direction.RIGHT || rightCell.getDirection()== Direction.CROSSING)){
                paths.add(rightCell);
            }

            if(leftCell!= null && (leftCell.getDirection()== Direction.LEFT || leftCell.getDirection()== Direction.CROSSING)){
                paths.add(leftCell);
            }

            if(leftCell!= null && upLeftCell!=null && leftCell.getDirection()==Direction.UP && upLeftCell.getDirection()== Direction.UP){ //pista linear dupla
                paths.add(upLeftCell);
            }

            if(rightCell!= null && upRightCell!=null && rightCell.getDirection()==Direction.UP && upRightCell.getDirection()== Direction.UP){ //pista linear dupla
                paths.add(upRightCell);
            }

            if(upCell!= null && upCell.getDirection() != Direction.DOWN){
                paths.add(upCell);
            }

            if(upCell == null){
                paths.add(grid.get(lineCount-1+" "+column));
            }



        cell.setPaths(paths.toArray(new Cell[0]));

    }
}
