package ru.cft.focusstart.task1;

public class MatrixGeneratorImpl implements MatrixGenerator {

    private final int size;

    public MatrixGeneratorImpl(int size) {
        this.size = size;
    }

    public int[][] generateMatrix() {
        int[][] data = new int[size + 1][size + 1];

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                if (i == 0 || j == 0) {
                    data[i][j] = i + j;
                } else {
                    data[i][j] = i * j;
                }
            }
        }

        return data;
    }

    public int getSize() {
        return size;
    }
}
