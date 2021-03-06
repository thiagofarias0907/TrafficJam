package instance.strategy;

import instance.world.cells.Cell;
import instance.world.cells.Direction;

import java.util.ArrayList;
import java.util.HashMap;

public class AddPathsRightDirection implements AddPathsStrategy {

    @Override
    public void addPaths(ArrayList<Cell> paths, HashMap<String, Cell> grid, int line, int column, Cell cell) {


        Cell upCell = grid.get((line-1)+" "+column);
        Cell downCell = grid.get((line+1)+" "+column);
        Cell rightCell = grid.get(line +" "+ (column+1));
        Cell downRightCell = grid.get((line-1) +" "+ (column+1));
        Cell upRightCell =grid.get((line+1) +" "+ (column+1));



        if(upCell!= null && (upCell.getDirection() == Direction.UP || upCell.getDirection() == Direction.CROSSING)){
            paths.add(upCell);
        }

        if(downCell!= null && (downCell.getDirection()==Direction.DOWN || downCell.getDirection()==Direction.CROSSING)){
            paths.add(downCell);
        }

        if (rightCell != null && rightCell.getDirection() != Direction.LEFT){
            paths.add(rightCell);
        }

        if(downCell!=null && upRightCell != null && upCell.getDirection() == Direction.RIGHT && upRightCell.getDirection() == Direction.RIGHT){
            paths.add(upRightCell);
        }

        if( downCell!=null && downRightCell != null && downCell.getDirection() == Direction.RIGHT&& downRightCell.getDirection() == Direction.RIGHT){
            paths.add(downRightCell);
        }



        cell.setPaths(paths.toArray(new Cell[0]));
    }
}
