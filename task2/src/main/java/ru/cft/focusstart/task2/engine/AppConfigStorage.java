package ru.cft.focusstart.task2.engine;

public class AppConfigStorage {

    private String pathToInputFile;
    private String pathToOutputFile;

    public String getPathToInputFile() {
        return pathToInputFile;
    }

    public void setPathToInputFile(String s) {
        pathToInputFile = s;
    }

    public String getPathToOutputFile() {
        return pathToOutputFile;
    }

    public void setPathToOutputFile(String s) {
        pathToOutputFile = s;
    }
}