package ru.cft.focusstart.task3.app;

import ru.cft.focusstart.task3.controller.Controller;
import ru.cft.focusstart.task3.model.MinesweeperManager;
import ru.cft.focusstart.task3.renderers.ViewRenderer;
import ru.cft.focusstart.task3.renderers.GameViewRenderer;
import ru.cft.focusstart.task3.view.*;

public class Application {

    static MainWindow mainWindow;
    static SettingsWindow settingsWindow;
    static HighScoresWindow highScoresWindow;
    static MinesweeperManager minesweeperManager;
    static Controller controller;
    static ViewRenderer viewRenderer;

    static int rows = 10;
    static int cols = 10;
    static int bombsCount = 10;

    public static void main(String[] args) {
//        MainWindow mainWindow = new MainWindow();
//        SettingsWindow settingsWindow = new SettingsWindow(mainWindow);
//        HighScoresWindow highScoresWindow = new HighScoresWindow(mainWindow);
//
//        mainWindow.setNewGameMenuAction(e -> { /* TODO */ });
//        mainWindow.setSettingsMenuAction(e -> settingsWindow.setVisible(true));
//        mainWindow.setHighScoresMenuAction(e -> highScoresWindow.setVisible(true));
//        mainWindow.setExitMenuAction(e -> mainWindow.dispose());
//        mainWindow.setCellListener((x, y, buttonType) -> { /* TODO */ });
//
//        mainWindow.createGameField(10, 10);
//        mainWindow.setVisible(true);
//
//        // TODO: There is a sample code below, remove it after try
//
//        mainWindow.setTimerValue(145);
//        mainWindow.setBombsCount(45);
//        mainWindow.setCellImage(0, 0, GameImage.EMPTY);
//        mainWindow.setCellImage(0, 1, GameImage.CLOSED);
//        mainWindow.setCellImage(0, 2, GameImage.MARKED);
//        mainWindow.setCellImage(0, 3, GameImage.BOMB);
//        mainWindow.setCellImage(1, 0, GameImage.NUM_1);
//        mainWindow.setCellImage(1, 1, GameImage.NUM_2);
//        mainWindow.setCellImage(1, 2, GameImage.NUM_3);
//        mainWindow.setCellImage(1, 3, GameImage.NUM_4);
//        mainWindow.setCellImage(1, 4, GameImage.NUM_5);
//        mainWindow.setCellImage(1, 5, GameImage.NUM_6);
//        mainWindow.setCellImage(1, 6, GameImage.NUM_7);
//        mainWindow.setCellImage(1, 7, GameImage.NUM_8);

        runNewGame(determineGameType(args));
    }

    public static void runNewGame(GameType gameType) {
        minesweeperManager = new MinesweeperManager(gameType);
        controller = new Controller(minesweeperManager);

        mainWindow = new MainWindow();
        settingsWindow = new SettingsWindow(mainWindow);
        highScoresWindow = new HighScoresWindow(mainWindow);
        viewRenderer = new GameViewRenderer(mainWindow);

        rows = minesweeperManager.getBoard().getRowsCount();
        cols = minesweeperManager.getBoard().getColumnsCount();

        mainWindow.createGameField(rows, cols);
        mainWindow.setTimerValue(0);
        bombsCount = minesweeperManager.getBoard().getMinesCount();
        mainWindow.setBombsCount(bombsCount);
        mainWindow.setVisible(true);

        minesweeperManager.attachView(viewRenderer);

        mainWindow.setNewGameMenuAction(e -> {
            controller.onNewGameClicked(gameType);
            mainWindow.setTimerValue(0);
            mainWindow.setBombsCount(minesweeperManager.getBoard().getMinesCount());
        });

        mainWindow.setSettingsMenuAction(e -> {
            settingsWindow.setVisible(true);
            settingsWindow.setGameTypeListener(gameType1 -> {

            });

        });
        mainWindow.setHighScoresMenuAction(e -> highScoresWindow.setVisible(true));
        mainWindow.setExitMenuAction(e -> mainWindow.dispose());
        mainWindow.setCellListener((x, y, buttonType) -> {
            controller.onCellClicked(x, y, buttonType);
        });
    }

    private static GameType determineGameType(String[] args) {
        if (args.length == 1) {
            return switch (args[0]) {
                case "MEDIUM" -> GameType.MEDIUM;
                case "EXPERT" -> GameType.EXPERT;
                default -> GameType.NOVICE;
            };
        }

        return GameType.NOVICE;
    }
}
