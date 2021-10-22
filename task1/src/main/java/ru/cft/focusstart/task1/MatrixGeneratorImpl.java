package ru.cft.focusstart.task1;

public class MatrixGeneratorImpl implements MatrixGenerator {

    private final int size;

    public MatrixGeneratorImpl(int size) {
        this.size = size;
    }

    /*Метод генерирует и возвращает восходящую матрицу заданного рамера.*/
    public int[][] generateMatrix() {
        var data = new int[size][size];

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                data[i][j] = (i + 1) * (j + 1);
            }
        }

        return data;
    }
}
