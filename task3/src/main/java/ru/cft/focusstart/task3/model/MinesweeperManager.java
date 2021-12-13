package ru.cft.focusstart.task3.model;

import ru.cft.focusstart.task3.app.Application;
import ru.cft.focusstart.task3.controller.ViewNotifier;
import ru.cft.focusstart.task3.renderers.ViewRenderer;
import ru.cft.focusstart.task3.renderers.ViewCellModifier;
import ru.cft.focusstart.task3.view.GameType;

import java.util.Map;
import java.util.Timer;

public class MinesweeperManager {
    private final int rowNumber;
    private final int columnNumber;
    private final int totalMines;
    private final int totalCells;
    private final ViewCellModifier viewCellModifier;
    private final ViewNotifier viewNotifier;
    private final Board board;
    private int minesLeft;
    private boolean endGame = false;
    private int seconds = 0;
    private final HighScoreTable highScoreTable;
    private Timer timer;
    private GameType gameType;

    public MinesweeperManager(GameType gameType) {
        this.rowNumber = getRowNumberByGameType(gameType);
        this.columnNumber = getColumnNumberByGameType(gameType);
        this.totalMines = getMinesCountByGameType(gameType);
        totalCells = rowNumber * columnNumber;

        board = new Board(rowNumber, columnNumber, totalMines);
        this.viewNotifier = new ViewNotifier();
        this.viewCellModifier = new ViewCellModifier(board, viewNotifier);
        this.highScoreTable = new HighScoreTable();
        minesLeft = totalMines;
        this.gameType = gameType;
    }

    public Board getBoard() {
        return board;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public void createNewGame(GameType gameType) {
        Application.main(new String[]{gameType.name()});
//        Application.runNewGame();
    }

    public void createNewGame() {
        Application.main(new String[]{"NOVICE"});
    }

    public void attachView(ViewRenderer viewRenderer) {
        viewNotifier.attachView(viewRenderer);
        notifyViewMinLeft();
    }

    public void openCell(int row, int column) {
        if (board.getClosedCells() == totalCells) {
            runTimer();
        }

        if (endGame) {
            return;
        }

        Cell cell = board.getCell(row, column);

        if (cell.isFlag()) {
            return;
        }

        if (cell.isMined()) {
            viewCellModifier.openTheMinedCell(row, column);
            createGameEnd(row, column, false);
        } else {
            viewCellModifier.openNonMinedCell(row, column);

            if (isVictory()) {
                createGameEnd(row, column, true);
            }
        }

        notifyViewMinLeft();
    }

    public void setStatusTimer(int status) {
        viewNotifier.notifyViewsNewTimerStatus(status);
    }

    public void openAroundCell(int row, int column) {
        if (endGame || !board.getCell(row, column).isOpened()) {
            return;
        }

        int totalMinesAroundForCell = CellsAnalyzer.countHowManyMinesAroundCell(board, row, column);
        int totalFlagsAroundForCell = CellsAnalyzer.countHowManyFlagsAroundCell(board, row, column);
        if (totalFlagsAroundForCell == totalMinesAroundForCell) {
            Map<Integer, Integer> minedMap = viewCellModifier.openAroundNonFlaggedCells(row, column);
            if (minedMap.size() > 0) {
                minedMap.forEach((rowIterator, columnIterator) -> createGameEnd(rowIterator, columnIterator, false));
                return;
            }
        }

        if (isVictory()) {
            createGameEnd(row, column, true);
        }
    }

    public void setFlag(int row, int column) {
        Cell cell = board.getCell(row, column);

        if (endGame || cell.isOpened()) {
            return;
        }

        if (minesLeft == 0 && !cell.isFlag()) {
            return;
        }

        boolean flagSet = !cell.isFlag();

        cell.setFlag(flagSet);
        viewCellModifier.setFlag(row, column, flagSet);

        minesLeft = flagSet ? --minesLeft : ++minesLeft;
        notifyViewMinLeft();
    }

    public void notifyViewHighScoreTable() {
        viewNotifier.notifyViewsHighScore(highScoreTable.getTopResults());
    }

    private void runTimer() {
        Timer timer = new Timer();
        MinesweeperTimer minesweeperTimer = new MinesweeperTimer(this);
        timer.schedule(minesweeperTimer, 1000, 1000);
        this.timer = timer;
    }

    private boolean isVictory() {
        return totalMines == board.getClosedCells();
    }

    private void notifyViewMinLeft() {
        viewNotifier.notifyViewMinLeft(minesLeft);
    }

    private void createGameEnd(int row, int column, boolean isWin) {
        viewCellModifier.openMines(rowNumber, columnNumber, row, column);

        timer.cancel();

        if (!endGame) {
            endGame = true;
            if (isWin) {
                createVictory();
            } else {
                createLoss();
            }
        }
    }

    private void createVictory() {
        Result currentResult = new Result("", gameType.name(), seconds);

        if (highScoreTable.isNewRecord(currentResult)) {
//            highScoreTable.updateHighScore(currentResult);
            viewNotifier.notifyViewsVictoryWithNewRecord(seconds);
        } else {
            viewNotifier.notifyViewsVictory(seconds);
        }
    }

    private void createLoss() {
        viewNotifier.notifyViewsLoss();
    }

    private int getRowNumberByGameType(GameType gameType) {
        return switch (gameType) {
            case NOVICE -> 9;
            case MEDIUM, EXPERT -> 16;
        };
    }

    private int getColumnNumberByGameType(GameType gameType) {
        return switch (gameType) {
            case NOVICE -> 9;
            case MEDIUM -> 16;
            case EXPERT -> 30;
        };
    }

    private int getMinesCountByGameType(GameType gameType) {
        return switch (gameType) {
            case NOVICE -> 10;
            case MEDIUM -> 40;
            case EXPERT -> 99;
        };
    }
}
