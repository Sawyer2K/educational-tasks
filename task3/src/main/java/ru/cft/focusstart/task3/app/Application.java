package ru.cft.focusstart.task3.app;

import ru.cft.focusstart.task3.controller.Controller;
import ru.cft.focusstart.task3.model.HighScoreTable;
import ru.cft.focusstart.task3.model.MinesweeperManager;
import ru.cft.focusstart.task3.model.Result;
import ru.cft.focusstart.task3.renderers.ViewRenderer;
import ru.cft.focusstart.task3.renderers.GameViewRenderer;
import ru.cft.focusstart.task3.view.*;

public class Application {

    static MainWindow mainWindow;
    static SettingsWindow settingsWindow;
    static HighScoresWindow highScoresWindow;
    static WinWindow winWindow;
    static LoseWindow loseWindow;
    static RecordsWindow recordsWindow;
    static HighScoreTable highScoreTable;

    static MinesweeperManager minesweeperManager;
    static Controller controller;
    static ViewRenderer viewRenderer;

    static String playerName;
    static GameType gameType = GameType.NOVICE;
    static int rows;
    static int cols;
    static int bombsCount;

    public static void main(String[] args) {
        minesweeperManager = new MinesweeperManager(gameType);
        controller = new Controller(minesweeperManager);
        highScoreTable = new HighScoreTable();

        mainWindow = new MainWindow();
        settingsWindow = new SettingsWindow(mainWindow);
        highScoresWindow = new HighScoresWindow(mainWindow);
        winWindow = new WinWindow(mainWindow);
        loseWindow = new LoseWindow(mainWindow);
        recordsWindow = new RecordsWindow(mainWindow);
        viewRenderer = new GameViewRenderer(mainWindow, winWindow, loseWindow, highScoresWindow, recordsWindow, highScoreTable);

        actionListenersInit();
        runNewGame();
    }

    public static void runNewGame() {
        minesweeperManager = new MinesweeperManager(gameType);
        controller = new Controller(minesweeperManager);

        rows = minesweeperManager.getBoard().getRowsCount();
        cols = minesweeperManager.getBoard().getColumnsCount();
        bombsCount = minesweeperManager.getBoard().getMinesCount();

        invalidateGraphic();
    }

    public static void updateHighScore(int seconds) {
        highScoreTable.updateHighScore(new Result(playerName, gameType.name(), seconds));
    }

    private static void invalidateGraphic() {
        mainWindow.createGameField(rows, cols);
        mainWindow.setTimerValue(0);
        mainWindow.setBombsCount(bombsCount);
        mainWindow.setVisible(true);
        minesweeperManager.attachView(viewRenderer);
    }

    private static void actionListenersInit() {
        mainWindow.setNewGameMenuAction(e -> controller.onNewGameClicked());
        mainWindow.setHighScoresMenuAction(e -> controller.onHighScoreClicked());
        mainWindow.setSettingsMenuAction(e -> {
            settingsWindow.setVisible(true);
            controller.onNewGameClicked();
            settingsWindow.dispose();
        });
        mainWindow.setAboutMenuAction(e -> new AboutWindow(mainWindow));
        mainWindow.setExitMenuAction(e -> mainWindow.dispose());
        mainWindow.setCellListener((x, y, buttonType) -> controller.onCellClicked(x, y, buttonType));

        settingsWindow.setGameTypeListener(type -> gameType = type);
        settingsWindow.setGameType(gameType);

        winWindow.setNewGameListener(e -> controller.onNewGameClicked());
        winWindow.setExitListener(e -> winWindow.dispose());

        loseWindow.setNewGameListener(e -> controller.onNewGameClicked());
        loseWindow.setExitListener(e -> loseWindow.dispose());

        recordsWindow.setNameListener(name -> playerName = name);
    }
}
