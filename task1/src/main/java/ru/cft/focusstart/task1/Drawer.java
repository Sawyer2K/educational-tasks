package ru.cft.focusstart.task1;

public class Drawer {

    private final String PIECE = "-";
    private final String VERTICAL_SEPARATOR = "|";
    private final String KNUCKLE = "+";
    private String firstColumn;
    private String standardColumn;
    private String horizontalSeparator;

    public Drawer() {

    }

    public void outputTheTable(MatrixGenerator matrixGeneratorImpl) {
        int[][] data = matrixGeneratorImpl.generateMatrix();
        int size = matrixGeneratorImpl.getSize();
        int minDigitCapacity = defineMinDigitCapacity(size);
        int maxDigitCapacity = defineMaxDigitCapacity(size);

        setFirstColumn(size);
        setStandardColumn(size);
        setHorizontalSeparator(size);

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                if (i == 0 & j == 0) {
                    System.out.printf("%" + (minDigitCapacity) + "s", " ");
                    System.out.print(VERTICAL_SEPARATOR);
                    continue;
                }

                if (j == 0) {
                    System.out.printf("%" + minDigitCapacity + "d", data[i][j]);
                } else {
                    System.out.printf("%" + maxDigitCapacity + "d", data[i][j]);
                }

                if (j != size) {
                    System.out.print(VERTICAL_SEPARATOR);
                }
            }
            System.out.print("\n");
            System.out.printf("%" + (maxDigitCapacity + 1) + "s", horizontalSeparator);
            System.out.print("\n");
        }
    }

    public void setFirstColumn(int size) {
        int temp = size;

        StringBuilder sb = new StringBuilder();

        while (temp > 0) {
            sb.append(PIECE);
            temp /= 10;
        }

        firstColumn = sb.toString();
    }

    public void setStandardColumn(int size) {
        int temp = size * size;

        StringBuilder sb = new StringBuilder();

        while (temp > 0) {
            sb.append(PIECE);
            temp /= 10;
        }

        standardColumn = sb.toString();
    }

    public void setHorizontalSeparator(int size) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < size + 1; i++) {
            if (i == 0) {
                sb.append(firstColumn);
            } else {
                sb.append(standardColumn);
            }

            if (i != size) {
                sb.append(KNUCKLE);
            }
        }

        horizontalSeparator = sb.toString();
    }

    public int defineMinDigitCapacity(int size) {
        int minDigitCapacity = 0;
        int temp = size;

        while (temp > 0) {
            minDigitCapacity++;
            temp /= 10;
        }

        return minDigitCapacity;
    }

    public int defineMaxDigitCapacity(int size) {
        int maxDigitCapacity = 0;
        int temp = size * size;

        while (temp > 0) {
            maxDigitCapacity++;
            temp /= 10;
        }

        return maxDigitCapacity;
    }
}
