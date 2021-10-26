package ru.cft.focusstart.task1;

public class Drawer {
    private final String HORIZONTAL_BORDER;
    private final String VERTICAL_BORDER;
    private final String CROSS_BORDER;
    private static final String LINE_SEPARATOR = System.lineSeparator();

    Drawer() {
        HORIZONTAL_BORDER = "-";
        VERTICAL_BORDER = "|";
        CROSS_BORDER = "+";
    }

    public String getAsText(Matrix matrix) {
        var matrixSize = matrix.getMatrixSize();
        var firstColumnWidth = getColumnWidth(matrixSize);
        var outputLineBuilder = new StringBuilder();

        /*Отрисовка самой первой пустой ячейки таблицы*/
        outputLineBuilder.append(String.format("%" + (firstColumnWidth) + "s", " "))
                         .append(VERTICAL_BORDER);

        var firstColumnUnderline = getColumnUnderline(firstColumnWidth);
        var standardColumnWidth = getColumnWidth(matrixSize * matrixSize);
        var standardColumnUnderline = getColumnUnderline(standardColumnWidth);
        var horizontalSeparatorForIndexRow = getHorizontalSeparator(firstColumnUnderline, standardColumnUnderline,
                                                                            matrix.getLongestRowSize());

        drawIndexRow(matrix, standardColumnWidth, horizontalSeparatorForIndexRow, outputLineBuilder);

        var currentRowWidth = matrix.getCurrentRowSize(0);

        for (int i = 0; i < matrixSize; i++) {
            String horizontalSeparator = "";

            /*отрисовка левого индексного столбца*/
            outputLineBuilder.append(String.format("%" + firstColumnWidth + "d", i + 1))
                             .append(VERTICAL_BORDER);

            /*Отрисовка самой таблицы умножения из заранее сгенерированной матрицы*/
            for (int j = 0; j < currentRowWidth - 1; j++) {
                currentRowWidth = matrix.getCurrentRowSize(i);
                horizontalSeparator = getHorizontalSeparator(firstColumnUnderline, standardColumnUnderline, currentRowWidth);

                outputLineBuilder.append(String.format("%" + standardColumnWidth + "d", matrix.getElementByIndexes(i, j)))
                                 .append(VERTICAL_BORDER);
            }
            outputLineBuilder.append(String.format("%" + standardColumnWidth + "d", matrix.getElementByIndexes(i, currentRowWidth - 1)))
                             .append(String.format("%s%" + (standardColumnWidth + 1) + "s%s", LINE_SEPARATOR,
                                                                        horizontalSeparator, LINE_SEPARATOR));
        }

        return outputLineBuilder.toString();
    }

    private void drawIndexRow(Matrix matrix, int standardColumnWidth, String horizontalSeparator, StringBuilder outputLineBuilder) {
        int i = 1;
        for (; i < matrix.getLongestRowSize(); i++) {
            outputLineBuilder.append(String.format("%" + standardColumnWidth + "d", i))
                             .append(VERTICAL_BORDER);
        }
        outputLineBuilder.append(String.format("%" + standardColumnWidth + "d", i))
                         .append(String.format("%s%" + (standardColumnWidth + 1) + "s%s", LINE_SEPARATOR,
                                                                        horizontalSeparator, LINE_SEPARATOR));
    }

    private String getColumnUnderline(int width) {
        return HORIZONTAL_BORDER.repeat(width);
    }

    private String getHorizontalSeparator(String firstColumnUnderline, String standardColumnUnderline, int size) {
        var sb = new StringBuilder();

        sb.append(firstColumnUnderline)
          .append(CROSS_BORDER);

        for (int i = 0; i < size - 1; i++) {
            sb.append(standardColumnUnderline)
              .append(CROSS_BORDER);
        }

        sb.append(standardColumnUnderline);

        return sb.toString();
    }

    private static int getColumnWidth(int size) {
        var width = 0;

        while (size > 0) {
            width++;
            size /= 10;
        }

        return width;
    }
}
