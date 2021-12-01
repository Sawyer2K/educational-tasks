package ru.cft.focusstart.task3.model;

import java.util.TimerTask;

public class MinesweeperTimer extends TimerTask {

    private int seconds;
    private final MinesweeperManager minesweeperManager;

    public MinesweeperTimer(MinesweeperManager minesweeperManager) {
        this.seconds = 0;
        this.minesweeperManager = minesweeperManager;
    }

    @Override
    public void run() {
        this.seconds++;
        minesweeperManager.setSeconds(this.seconds);
        minesweeperManager.setStatusTimer(this.seconds);
    }
}

