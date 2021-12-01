package ru.cft.focusstart.task3.renderers;

import ru.cft.focusstart.task3.model.Result;
import ru.cft.focusstart.task3.view.GameImageIconMap;
import ru.cft.focusstart.task3.view.LoseWindow;
import ru.cft.focusstart.task3.view.MainWindow;

public class GameViewRenderer implements ViewRenderer {
    MainWindow mainWindow;

    public GameViewRenderer(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void renderVictory(int seconds) {

    }

    @Override
    public void renderVictoryWithNewRecord(int seconds) {

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

    }

    @Override
    public void updateTimerStatus(int status) {
    }

    @Override
    public void renderAbout() {

    }

    @Override
    public void renderHighScore(Result[] results) {

    }
}
