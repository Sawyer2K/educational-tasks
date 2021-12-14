package ru.cft.focusstart.task3.model;

public final class CellsAnalyzer {

    public static boolean isValidCell(Board board, int row, int column) {
        return (column >= 0 && row >= 0)
                && (row < board.getRowsCount() && column < board.getColumnsCount());
    }

    public static int countHowManyFlagsAroundCell(Board board, int row, int column) {
        int flagsCountAroundCell = 0;

        for (int rowIterator = row - 1; rowIterator <= row + 1; rowIterator++) {
            for (int columnIterator = column - 1; columnIterator <= column + 1; columnIterator++) {
                if (isValidCell(board, rowIterator, columnIterator) && board.getCell(rowIterator, columnIterator).isFlag()) {
                    flagsCountAroundCell++;
                }
            }
        }

        return flagsCountAroundCell;
    }

    public static int countHowManyMinesAroundCell(Board board, int row, int column) {
        int minCounterAroundCell = 0;

        for (int rowIterator = row - 1; rowIterator <= row + 1; rowIterator++) {
            for (int columnIterator = column - 1; columnIterator <= column + 1; columnIterator++) {
                if (isValidCell(board, rowIterator, columnIterator)) {
                    if (board.getCell(rowIterator, columnIterator).isMined()) {
                        minCounterAroundCell++;
                    }
                }
            }
        }

        return minCounterAroundCell;
    }
}
