package instance.strategy;

import instance.world.cells.Cell;
import instance.world.cells.Direction;

import java.util.ArrayList;
import java.util.HashMap;


public class AddPathsDownDirection implements AddPathsStrategy {

    @Override
    public void addPaths(ArrayList<Cell> paths, HashMap<String, Cell> grid, int line, int column, Cell cell) {


        Cell downCell = grid.get((line+1)+" "+column);
        Cell leftCell = grid.get(line +" "+ (column-1));
        Cell rightCell = grid.get(line +" "+ (column+1)) ;
        Cell downLeftCell = grid.get((line+1) +" "+ (column-1));
        Cell downRightCell =grid.get((line+1) +" "+ (column+1));



            if(rightCell!= null && (rightCell.getDirection()== Direction.RIGHT || rightCell.getDirection()== Direction.CROSSING)){
                paths.add(rightCell);
            }

            if(leftCell!= null && (leftCell.getDirection()== Direction.LEFT || leftCell.getDirection()== Direction.CROSSING)){
                paths.add(leftCell);
            }

            if(leftCell!= null && downLeftCell!=null &&  leftCell.getDirection()==Direction.DOWN && downLeftCell.getDirection()== Direction.DOWN){
                paths.add(downLeftCell);
            }

            if( rightCell!= null && downRightCell!=null && rightCell.getDirection()==Direction.DOWN && downRightCell.getDirection()== Direction.DOWN){
                paths.add(downRightCell);
            }

            if(downCell!= null && downCell.getDirection() != Direction.UP){
                paths.add(downCell);
            }




        cell.setPaths(paths.toArray(new Cell[0]));
    }
}
