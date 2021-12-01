package ru.cft.focusstart.task3.app;

import ru.cft.focusstart.task3.controller.Controller;
import ru.cft.focusstart.task3.model.MinesweeperManager;
import ru.cft.focusstart.task3.renderers.ViewRenderer;
import ru.cft.focusstart.task3.renderers.GameViewRenderer;
import ru.cft.focusstart.task3.view.*;

public class Application {
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

        run(determineGameType(args));
    }

    private static void run(GameType gameType) {
        MainWindow mainWindow = new MainWindow();
        SettingsWindow settingsWindow = new SettingsWindow(mainWindow);
        HighScoresWindow highScoresWindow = new HighScoresWindow(mainWindow);
        mainWindow.createGameField(10, 10);
        mainWindow.setVisible(true);

        MinesweeperManager minesweeperManager = new MinesweeperManager(gameType);
        Controller controller = new Controller(minesweeperManager);
        ViewRenderer viewRenderer = new GameViewRenderer(mainWindow);
        minesweeperManager.attachView(viewRenderer);

        mainWindow.setNewGameMenuAction(e -> {

        });
        mainWindow.setSettingsMenuAction(e -> settingsWindow.setVisible(true));
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
