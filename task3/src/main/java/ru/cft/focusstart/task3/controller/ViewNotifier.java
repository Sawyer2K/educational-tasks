package ru.cft.focusstart.task3.controller;

import ru.cft.focusstart.task3.model.Result;
import ru.cft.focusstart.task3.renderers.ViewRenderer;

import java.util.ArrayList;
import java.util.List;

public final class ViewNotifier {
    private final List<ViewRenderer> views = new ArrayList<>();

    public void attachView(ViewRenderer crossView) {
        views.add(crossView);
    }

    public void notifyViewNewCellStatus(int row, int column, String status) {
        views.forEach(crossView -> crossView.updateCell(row, column, status));
    }

    public void notifyViewMinLeft(int status) {
        views.forEach(crossView -> crossView.updateMinLeftStatus(status));
    }

    public void notifyViewsNewTimerStatus(int status) {
        views.forEach(crossView -> crossView.updateTimerStatus(status));
    }

    public void notifyViewsLoss() {
        views.forEach(ViewRenderer::renderLoss);
    }

    public void notifyViewsVictory(int seconds) {
        views.forEach(crossView -> crossView.renderVictory(seconds));
    }

    public void notifyViewsVictoryWithNewRecord(int seconds) {
        views.forEach(crossView -> crossView.renderVictoryWithNewRecord(seconds));
    }

    public void notifyViewsHighScore(Result[] result) {
        views.forEach(crossView -> crossView.renderHighScore(result));
    }
}
