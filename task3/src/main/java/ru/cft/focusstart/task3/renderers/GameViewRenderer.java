package ru.cft.focusstart.task3.renderers;

import ru.cft.focusstart.task3.model.Result;
import ru.cft.focusstart.task3.view.*;

public class GameViewRenderer implements ViewRenderer {
    MainWindow mainWindow;

    public GameViewRenderer(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void renderVictory(int seconds) {
        new WinWindow(mainWindow);
    }

    @Override
    public void renderVictoryWithNewRecord(int seconds) {
        new WinWindow(mainWindow);
    }

    @Override
    public void renderLoss() {
        new LoseWindow(mainWindow);
    }

    @Override
    public void updateCell(int row, int column, String code) {
        if (GameImageIconMap.isIconCodeValid(code)) {
            mainWindow.setCellImage(row, column, GameImageIconMap.getCellIconMap(code));
        }
    }

    @Override
    public void updateMinLeftStatus(int status) {
        //mainWindow.setBombsCount(status);
    }

    @Override
    public void updateTimerStatus(int status) {
        mainWindow.setTimerValue(status);
    }

    @Override
    public void renderAbout() {

    }

    @Override
    public void renderHighScore(Result[] results) {
        new HighScoresWindow(mainWindow);
    }
}
