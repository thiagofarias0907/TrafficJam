package instance.strategy;

import instance.world.cells.Cell;
import instance.world.cells.Direction;

import java.util.ArrayList;
import java.util.HashMap;

public class AddPathsLeftDirection implements AddPathsStrategy {
    @Override
    public void addPaths(ArrayList<Cell> paths, HashMap<String, Cell> grid, int line, int column, Cell cell, int lineCount, int columnCount) {

        Cell upCell = grid.get((line-1)+" "+column);
        Cell downCell = grid.get((line+1)+" "+column);
        Cell leftCell = grid.get(line +" "+ (column-1));
        Cell downLeftCell = grid.get((line-1) +" "+ (column-1));
        Cell upLeftCell =grid.get((line+1) +" "+ (column-1));



        if(upCell!= null && (upCell.getDirection() == Direction.UP || upCell.getDirection() == Direction.CROSSING)){
            paths.add(upCell);
        }

        if(downCell!= null && (downCell.getDirection()==Direction.DOWN || downCell.getDirection()==Direction.CROSSING)){
            paths.add(downCell);
        }

        if (leftCell != null && leftCell.getDirection() != Direction.RIGHT){
            paths.add(leftCell);
        }

        if(upCell!=null && upLeftCell != null && upCell.getDirection() == Direction.LEFT && upLeftCell.getDirection() == Direction.LEFT){
            paths.add(upLeftCell);
        }

        if(downCell!= null && downLeftCell != null && downCell.getDirection() == Direction.LEFT&& downLeftCell.getDirection() == Direction.LEFT){
            paths.add(downLeftCell);
        }

        if(leftCell==null){
            paths.add(grid.get(line+" "+(columnCount-1)));
        }






        cell.setPaths(paths.toArray(new Cell[0]));
    }
}
