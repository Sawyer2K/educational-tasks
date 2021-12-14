package ru.cft.focusstart.task3.renderers;

import ru.cft.focusstart.task3.model.Result;

public interface ViewRenderer {

    void renderVictory(int successRate);

    void renderVictoryWithNewRecord(int successRate);

    void renderLoss();

    void updateCell(int row, int column, String code);

    void updateMinLeftStatus(int status);

    void updateTimerStatus(int status);

    void renderHighScore(Result[] results);
}
