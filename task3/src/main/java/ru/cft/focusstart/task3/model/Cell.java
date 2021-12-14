package ru.cft.focusstart.task3.model;

public class Cell {

    private boolean opened;
    private boolean mined;
    private boolean flag;
    private boolean empty;

    public Cell() {
        opened = false;
        mined = false;
        flag = false;
        empty = false;
    }

    public void open() {
        opened = true;
    }

    public boolean isOpened() {
        return opened;
    }

    public boolean isMined() {
        return mined;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void setEmpty(boolean state) {
        empty = state;
    }

    public void insertMine() {
        mined = true;
    }
}

