package instance.strategy;

import instance.world.cells.Cell;
import instance.world.cells.Direction;

import java.util.ArrayList;
import java.util.HashMap;


//todo: crossing (undef directions)
public class AddPathsDownDirection implements AddPathsStrategy {

    @Override
    public void addPaths(ArrayList<Cell> paths, HashMap<String, Cell> grid, int line, int column, Cell cell, int lineCount, int RowCount) {


        Cell downCell = grid.get((line+1)+" "+column);
        Cell leftCell = grid.get(line +" "+ (column-1));
        Cell rightCell = grid.get(line +" "+ (column+1)) ;
        Cell downLeftCell = grid.get((line+1) +" "+ (column-1));
        Cell downRightCell =grid.get((line+1) +" "+ (column+1));



            if(rightCell!= null && (rightCell.getDirection()== Direction.RIGHT || rightCell.getDirection()== Direction.UNDEF)){
                paths.add(rightCell);
            }

            if(leftCell!= null && (leftCell.getDirection()== Direction.LEFT || leftCell.getDirection()== Direction.UNDEF)){
                paths.add(leftCell);
            }

            if(leftCell!= null && leftCell.getDirection()==Direction.DOWN && downLeftCell.getDirection()== Direction.DOWN){ //pista linear dupla
                paths.add(downLeftCell);
            }

            if(rightCell!= null && rightCell.getDirection()==Direction.DOWN && downRightCell.getDirection()== Direction.DOWN){ //pista linear dupla
                paths.add(downRightCell);
            }

            if(downCell!= null && downCell.getDirection() != Direction.UP){
                paths.add(downCell);
            }

            if(downCell==null){
                paths.add(grid.get(0+" "+column));
            }



        cell.setPaths(paths.toArray(new Cell[0]));
    }
}
