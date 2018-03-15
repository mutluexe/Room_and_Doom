package map;

import sample.*;

public class Grid {

    public Cell[][] grid;//Creating cell array

    public Grid() {

        grid = new Cell[(int) Settings.ROW_CELL_COUNT][(int) Settings.COLUMN_CELL_COUNT];
    }

    public void addCell(Cell cell) {

        grid[(int) (cell.position.row)][(int) cell.position.column] = cell;

    }

    public Cell getCell(int row, int column) {

        return grid[row][column];

    }


}
