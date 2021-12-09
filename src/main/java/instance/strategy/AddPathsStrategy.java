package instance.strategy;

import instance.world.cells.Cell;

import java.util.ArrayList;
import java.util.HashMap;

public interface AddPathsStrategy {

    void addPaths(ArrayList<Cell> paths, HashMap<String, Cell> grid, int line, int column, Cell cell);
}
