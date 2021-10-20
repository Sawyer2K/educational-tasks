package ru.cft.focusstart.task1;

public class Drawer {

    int dimensionality;
    int maxDigitCapacity;
    private final String PIECE = "-";
    private final String VERTICAL_SEPARATOR = "|";
    private final String KNUCKLE = "+";
    private String firstColumn;
    private String standardColumn;
    private String horizontalSeparator;

    public Drawer(int dimensionality) {
        this.dimensionality = dimensionality;
        setDigitCapacity();
    }

    public void outputTheTable(DataHolder dataHolder) {
        int[][] data = dataHolder.getData();
        String formattingMessage = "%-" + maxDigitCapacity + "d";

        int border = dimensionality;
        for (int i = 0; i < dimensionality; i++) {
            for (int j = 0; j < dimensionality; j++) {
                System.out.printf(formattingMessage, data[i][j]);
                if (j != dimensionality - 1) {
                    System.out.print(VERTICAL_SEPARATOR);
                }
            }
        }
    }

    public void setFirstColumn() {
        int temp = dimensionality;

        StringBuilder sb = new StringBuilder();

        while (temp > 0) {
            sb.append(PIECE);
            temp /= 10;
        }

        firstColumn = sb.toString();
    }

    public void setOtherColumn() {
        int temp = dimensionality * dimensionality;

        StringBuilder sb = new StringBuilder();

        while (temp > 0) {
            sb.append(PIECE);
            temp /= 10;
        }

        standardColumn = sb.toString();
    }

    public void setHorizontalSeparator() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < dimensionality; i++) {
            if (i == 0) {
                sb.append(firstColumn);
            } else {
                sb.append(standardColumn);
            }

            if (i != dimensionality - 1) {
                sb.append(KNUCKLE);
            }
        }

        horizontalSeparator = sb.toString();
    }

    public void setDigitCapacity() {
        int temp = dimensionality * dimensionality;

        while (temp > 0) {
            maxDigitCapacity++;
            temp /= 10;
        }
    }
}
