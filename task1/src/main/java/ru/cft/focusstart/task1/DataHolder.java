package ru.cft.focusstart.task1;

public class DataHolder {

    private int[][] data;

    public DataHolder(int dimensionality) {
        data = new int[dimensionality + 1][dimensionality + 1];
        fillingTheArray();
    }

    public void fillingTheArray() {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                if (i == 0 || j == 0) {
                    data[i][j] = i + j;
                } else {
                    data[i][j] = i * j;
                }
            }
        }
    }

    public int[][] getData() {
        return data;
    }
}
