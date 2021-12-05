package instance.strategy;

import instance.world.cells.Cell;

import java.util.ArrayList;
import java.util.HashMap;

public class AddPathsUndefDirection implements AddPathsStrategy{

    @Override
    public void addPaths(ArrayList<Cell> paths, HashMap<String, Cell> grid, int line, int column, Cell cell, int lineCount, int RowCount) {


        Cell upCell = grid.get((line-1)+" "+column);
        Cell downCell = grid.get((line+1)+" "+column);
        Cell leftCell = grid.get(line +" "+ (column-1));
        Cell rightCell = grid.get(line +" "+ (column+1)) ;


        switch (cell.getValue()){

            case 5->{
                paths.add(upCell);
                break;
            }
            case 6->{
                paths.add(rightCell);
                break;
            }
            case 7->{
                paths.add(downCell);
                break;
            }
            case 8->{
                paths.add(leftCell);
                break;
            }
            case 9->{
                paths.add(upCell);
                paths.add(rightCell);
                break;
            }
            case 10->{
                paths.add(upCell);
                paths.add(leftCell);
                break;
            }
            case 11->{
                paths.add(downCell);
                paths.add(rightCell);
                break;
            }
            case 12->{
                paths.add(downCell);
                paths.add(leftCell);
                break;
            }

        }

        cell.setPaths(paths.toArray(new Cell[0]));


    }
}
