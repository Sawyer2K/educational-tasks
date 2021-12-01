package ru.cft.focusstart.task3.controller;

import ru.cft.focusstart.task3.model.MinesweeperManager;
import ru.cft.focusstart.task3.view.ButtonType;
import ru.cft.focusstart.task3.view.GameType;

public class Controller {

    private final MinesweeperManager minesweeperManager;

    public Controller(MinesweeperManager minesweeperManager) {
        this.minesweeperManager = minesweeperManager;
    }

    public void onCellClicked(int row, int column, ButtonType buttonType) {
        switch (buttonType) {
            case LEFT_BUTTON -> minesweeperManager.openCell(row, column);
            case RIGHT_BUTTON -> minesweeperManager.setFlag(row, column);
            case MIDDLE_BUTTON -> minesweeperManager.openAroundCell(row, column);
        }
    }

    public void onNewGameClicked(GameType gameType) {
        minesweeperManager.createNewGame(gameType);
    }

    public void onHighScoreClicked() {
        minesweeperManager.notifyViewHighScoreTable();
    }

    public void handleUserAddPlayerName(String playerName) {
        minesweeperManager.setPlayerName(playerName);
    }
}
