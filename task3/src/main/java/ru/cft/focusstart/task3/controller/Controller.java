package ru.cft.focusstart.task3.controller;

import ru.cft.focusstart.task3.model.MinesweeperManager;
import ru.cft.focusstart.task3.view.ButtonType;

public class Controller {

    private final MinesweeperManager minesweeperManager;

    public Controller(MinesweeperManager minesweeperManager) {
        this.minesweeperManager = minesweeperManager;
    }

    public void onCellClicked(int row, int column, ButtonType buttonType) {
        switch (buttonType) {
            case LEFT_BUTTON -> minesweeperManager.openCell(row, column);
            case MIDDLE_BUTTON -> minesweeperManager.setFlag(row, column);
            case RIGHT_BUTTON -> minesweeperManager.openAroundCell(row, column);
        }
    }

    public void onNewGameClicked() {
        minesweeperManager.createNewGame();
    }

    public void onHighScoreClicked() {
        minesweeperManager.notifyViewHighScoreTable();
    }
}
