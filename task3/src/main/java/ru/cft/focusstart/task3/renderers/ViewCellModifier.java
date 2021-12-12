package ru.cft.focusstart.task3.renderers;

import ru.cft.focusstart.task3.model.CellsAnalyzer;
import ru.cft.focusstart.task3.controller.ViewNotifier;
import ru.cft.focusstart.task3.model.Board;
import ru.cft.focusstart.task3.model.Cell;
import ru.cft.focusstart.task3.view.GameImageIconMap;

import java.util.HashMap;
import java.util.Map;

public class ViewCellModifier {
    private final Board board;
    private final ViewNotifier viewNotifier;

    public ViewCellModifier(Board board, ViewNotifier viewNotifier) {
        this.board = board;
        this.viewNotifier = viewNotifier;
    }

    public void openNonMinedCell(int row, int column) {
        Cell cell = board.getCell(row, column);

        if (cell.isOpened() || cell.isFlag()) {
            return;
        } else {
            cell.open();
            board.decrementClosedCells();
        }

        int minesAroundCellCount = CellsAnalyzer.countHowManyMinesAroundCell(board, row, column);

        if (minesAroundCellCount == 0) {
            cell.setEmpty(true);
            openAllNonMinedCellsAround(row, column);
        }

        String iconCode = String.valueOf(minesAroundCellCount);

        if (!GameImageIconMap.isIconCodeValid(iconCode)) {
//            log error - Не удалось получить иконку
            return;
        }

        viewNotifier.notifyViewNewCellStatus(row, column, iconCode);
    }

    public void openTheMinedCell(int row, int column) {
        viewNotifier.notifyViewNewCellStatus(row, column, "bomb");
    }

    public Map<Integer, Integer> openAroundNonFlaggedCells(int row, int column) {
        Map<Integer, Integer> minedMap = new HashMap<>();
        for (int rowIterator = row - 1; rowIterator <= row + 1; rowIterator++) {
            for (int columnIterator = column - 1; columnIterator <= column + 1; columnIterator++) {
                if (CellsAnalyzer.isValidCell(board, rowIterator, columnIterator) && !board.getCell(rowIterator, columnIterator).isFlag()) {
                    if (board.getCell(rowIterator, columnIterator).isMined()) {
                        openTheMinedCell(rowIterator, columnIterator);
                        minedMap.put(rowIterator, columnIterator);
                    } else {
                        openNonMinedCell(rowIterator, columnIterator);
                    }
                }
            }
        }

        return minedMap;
    }

    public void openAllNonMinedCellsAround(int row, int column) {
        for (int bypassRow = row - 1; bypassRow <= row + 1; bypassRow++) {
            for (int bypassColumn = column - 1; bypassColumn <= column + 1; bypassColumn++) {
                if ((bypassRow == row && bypassColumn == column) || !CellsAnalyzer.isValidCell(board, bypassRow, bypassColumn)) {
                    continue;
                }

                openNonMinedCell(bypassRow, bypassColumn);
            }
        }
    }

    public void setFlag(int row, int column, boolean flag) {
        if (flag) {
            viewNotifier.notifyViewNewCellStatus(row, column, "marked");
        } else {
            viewNotifier.notifyViewNewCellStatus(row, column, "closed");
        }
    }

    public void openMines(int numberRow, int numberColumn, int bombedRow, int bombedColumn) {
        for (int row = 0; row < numberRow; row++) {
            for (int column = 0; column < numberColumn; column++) {
                if (row == bombedRow && column == bombedColumn) {
                    continue;
                }

                if (board.getCell(row, column).isMined()) {
                    if (board.getCell(row, column).isFlag()) {
                        viewNotifier.notifyViewNewCellStatus(row, column, "0");
                    } else {
                        viewNotifier.notifyViewNewCellStatus(row, column, "bomb");
                    }
                }
            }
        }
    }
}