package ru.cft.focusstart.task3.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cft.focusstart.task3.app.Application;
import ru.cft.focusstart.task3.controller.ViewNotifier;
import ru.cft.focusstart.task3.renderers.ViewRenderer;
import ru.cft.focusstart.task3.renderers.ViewCellModifier;
import ru.cft.focusstart.task3.view.GameType;

import java.util.Map;
import java.util.Random;
import java.util.Timer;

public class MinesweeperManager {

    private static final Logger log = LoggerFactory.getLogger(MinesweeperManager.class.getName());

    private final Board board;
    private final int rowNumber;
    private final int columnNumber;
    private final int totalMines;
    private final int totalCells;
    private final ViewCellModifier viewCellModifier;
    private final HighScoreTable highScoreTable;
    private final ViewNotifier viewNotifier;
    private final GameType gameType;
    private boolean endGame = false;
    private int minesLeft;
    private int seconds = 0;
    private Timer timer;

    public MinesweeperManager(GameType gameType) {
        this.rowNumber = GameParametersIdentifier.getRowCountByGameType(gameType);
        this.columnNumber = GameParametersIdentifier.getColumnCountByGameType(gameType);
        this.totalMines = GameParametersIdentifier.getMinesCountByGameType(gameType);
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

    public void createNewGame() {
        Application.runNewGame();
    }

    public void attachView(ViewRenderer viewRenderer) {
        viewNotifier.attachView(viewRenderer);
        notifyViewMinLeft();
    }

    public void openCell(int row, int column) {
        if (board.getClosedCells() == totalCells) {
            runTimer();
            fillingBoardWithMines(board, totalMines);
        }

        if (endGame) {
            return;
        }

        var cell = board.getCell(row, column);

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

        var totalMinesAroundForCell = CellsAnalyzer.countHowManyMinesAroundCell(board, row, column);
        var totalFlagsAroundForCell = CellsAnalyzer.countHowManyFlagsAroundCell(board, row, column);
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
        var cell = board.getCell(row, column);

        if (endGame || cell.isOpened()) {
            return;
        }

        if (minesLeft == 0 && !cell.isFlag()) {
            return;
        }

        var flagSet = !cell.isFlag();

        cell.setFlag(flagSet);
        viewCellModifier.setFlag(row, column, flagSet);

        minesLeft = flagSet ? --minesLeft : ++minesLeft;
        notifyViewMinLeft();
    }

    public void notifyViewHighScoreTable() {
        viewNotifier.notifyViewsHighScore(highScoreTable.getTopResults());
    }

    private void runTimer() {
        log.info("Игровой таймер запущен..");

        var timer = new Timer();
        var minesweeperTimer = new MinesweeperTimer(this);
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
        log.info("Игра окончена, открываем мины..");

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
        log.info("Генерируем победу..");

        var currentResult = new Result("", gameType.name(), seconds);

        if (highScoreTable.isNewRecord(currentResult)) {
            viewNotifier.notifyViewsVictoryWithNewRecord(seconds);
        } else {
            viewNotifier.notifyViewsVictory(seconds);
        }
    }

    private void createLoss() {
        log.info("Генерируем поражение..");

        viewNotifier.notifyViewsLoss();
    }

    private void fillingBoardWithMines(final Board board, int mines) {
        log.info("Заполняем игровое поле минами. Количество мин {}..", mines);

        var numberOfMinesSet = 0;

        for (var row = 0; row < board.getRowsCount(); row++) {
            for (var col = 0; col < board.getColumnsCount(); col++) {
                if (numberOfMinesSet < mines) {
                    board.getCell(row, col).insertMine();
                    numberOfMinesSet++;
                }
            }
        }
        shuffleBoard(board);

        log.info("Игровое поле \"заминировано\".");
    }

    private void shuffleBoard(Board board) {
        var random = new Random();

        for (var row = board.getRowsCount() - 1; row > 0; row--) {
            for (var col = board.getColumnsCount() - 1; col > 0; col--) {
                var rowRandom = random.nextInt(row + 1);
                var colRandom = random.nextInt(col + 1);

                var temp = board.getCell(row, col);
                board.setCell(board.getCell(rowRandom, colRandom), row, col);
                board.setCell(temp, rowRandom, colRandom);
            }
        }
    }
}
