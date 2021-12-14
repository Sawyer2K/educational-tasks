package ru.cft.focusstart.task3.renderers;

import ru.cft.focusstart.task3.app.Application;
import ru.cft.focusstart.task3.model.HighScoreTable;
import ru.cft.focusstart.task3.model.Result;
import ru.cft.focusstart.task3.view.*;

public class GameViewRenderer implements ViewRenderer {

    MainWindow mainWindow;
    WinWindow winWindow;
    LoseWindow loseWindow;
    HighScoresWindow highScoresWindow;
    RecordsWindow recordsWindow;
    HighScoreTable highScoreTable;

    public GameViewRenderer(MainWindow mainWindow, WinWindow winWindow, LoseWindow loseWindow, HighScoresWindow highScoresWindow,
                            RecordsWindow recordsWindow, HighScoreTable highScoreTable) {
        this.mainWindow = mainWindow;
        this.winWindow = winWindow;
        this.loseWindow = loseWindow;
        this.highScoresWindow = highScoresWindow;
        this.recordsWindow = recordsWindow;
        this.highScoreTable = highScoreTable;
    }

    @Override
    public void renderVictory(int seconds) {
        winWindow.setVisible(true);

    }

    @Override
    public void renderVictoryWithNewRecord(int seconds) {
        recordsWindow.setVisible(true);
        Application.updateHighScore(seconds);
    }

    @Override
    public void renderLoss() {
        loseWindow.setVisible(true);
    }

    @Override
    public void updateCell(int row, int column, String code) {
        if (GameImageIconMap.isIconCodeValid(code)) {
            mainWindow.setCellImage(row, column, GameImageIconMap.getCellIconMap(code));
        }
    }

    @Override
    public void updateMinLeftStatus(int status) {
        mainWindow.setBombsCount(status);
    }

    @Override
    public void updateTimerStatus(int status) {
        mainWindow.setTimerValue(status);
    }

    @Override
    public void renderHighScore(Result[] results) {
        highScoresWindow.setNoviceRecord(highScoreTable.getNovicePlayerName(), highScoreTable.getNoviceTimeValue());
        highScoresWindow.setMediumRecord(highScoreTable.getMediumPlayerName(), highScoreTable.getMediumTimeValue());
        highScoresWindow.setExpertRecord(highScoreTable.getExpertPlayerName(), highScoreTable.getExpertTimeValue());
        highScoresWindow.setVisible(true);
    }
}
