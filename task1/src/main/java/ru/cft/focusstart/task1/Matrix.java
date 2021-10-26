package ru.cft.focusstart.task1;

public interface Matrix {
    int getElementByIndexes(int i, int j);
    int getMatrixSize();
    int getCurrentRowSize(int i);
    int getLongestRowSize();
}
