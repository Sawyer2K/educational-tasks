package ru.cft.focusstart.task3.model;

public class Board {

    public static final int DEFAULT_ROWS_COUNT = 9;
    public static final int DEFAULT_COLUMNS_COUNT = 9;
    public static final int DEFAULT_MINES_COUNT = 10;


    private final Cell[][] cells;
    private final int rowsCount;
    private final int columnsCount;
    private final int minesCount;
    private int closedCells;

    public Board() {
        this(DEFAULT_ROWS_COUNT, DEFAULT_COLUMNS_COUNT, DEFAULT_MINES_COUNT);
        setClosedCells(rowsCount * columnsCount);
    }

    public Board(int rowsCount, int columnsCount, int minesCount) {
        this.rowsCount = Math.max(rowsCount, DEFAULT_ROWS_COUNT);
        this.columnsCount = Math.max(columnsCount, DEFAULT_COLUMNS_COUNT);
        this.minesCount = minesCount;
        setClosedCells(rowsCount * columnsCount);

        cells = BoardBuilder.buildBoard(rowsCount, columnsCount, minesCount);
    }

    public Cell getCell(int row, int column) {
        return cells[row][column];
    }

    public int getRowsCount() {
        return this.rowsCount;
    }

    public int getColumnsCount() {
        return this.columnsCount;
    }

    public int getClosedCells() {
        return closedCells;
    }

    public void decrementClosedCells() {
        if (closedCells >= 1) {
            closedCells--;
        }
    }

    public void setClosedCells(int closedCells) {
        this.closedCells = closedCells;
    }

    public int getMinesCount() {
        return minesCount;
    }
}
