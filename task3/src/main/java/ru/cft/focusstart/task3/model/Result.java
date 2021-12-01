package ru.cft.focusstart.task3.model;

public class Result {

    private String playerName;
    private String gameType;
    private int seconds;

    public Result() {}

    public Result(String playerName, String gameType, int seconds) {
        this.playerName = playerName;
        this.gameType = gameType;
        this.seconds = seconds;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
}
