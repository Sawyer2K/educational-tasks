package ru.cft.focusstart.task1;

public class MultiplicationMatrix implements Matrix {
    int[][] data;

    MultiplicationMatrix(int size) {
        data = new int[size][size];

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                data[i][j] = (i + 1) * (j + 1);
            }
        }
    }

    public int getElementByIndexes(int i, int j) {
        return data[i][j];
    }
    public int getMatrixSize() {
        return data.length;
    }
    public int getCurrentRowSize(int i) {
        return data[i].length;
    }
    public int getLongestRowSize() {
        int longestRowLength = 0;
        for (int i = 0; i < getMatrixSize(); i++) {
            if (getCurrentRowSize(i) > longestRowLength) {
                longestRowLength = getCurrentRowSize(i);
            }
        }

        return longestRowLength;
    }
}
