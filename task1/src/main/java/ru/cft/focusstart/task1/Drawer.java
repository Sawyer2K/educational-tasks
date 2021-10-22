package ru.cft.focusstart.task1;

public class Drawer {

    private final String HORIZONTAL_BORDER = "-";
    private final String VERTICAL_BORDER = "|";
    private final String CROSS_BORDER = "+";

    public Drawer() {

    }

    public String outputTheTable(MatrixGenerator matrixGenerator) {
        var data = matrixGenerator.generateMatrix();
        var size = data.length;
        var biggestMatrixValue = size * size;

        /*Определение ширины колонок в разрядах*/
        var firstColumnWidth = getColumnWidth(size);
        var standardColumnWidth = getColumnWidth(biggestMatrixValue);

        //Инициализация строкового представления разделителей для первой и стандартной колонок, а так же общего
        //горизонтального разделителя таблицы
        var firstColumnUnderline = getColumnUnderline(firstColumnWidth);
        var standardColumnUnderline = getColumnUnderline(standardColumnWidth);
        var horizontalSeparator = getHorizontalSeparator(firstColumnUnderline, standardColumnUnderline, size);

        var outputLineBuilder = new StringBuilder();

        /*Отрисовка самой первой пустой ячейки таблицы*/
        outputLineBuilder.append(String.format("%" + (firstColumnWidth) + "s", " "));
        outputLineBuilder.append(VERTICAL_BORDER);

        drawIndexRow(data, standardColumnWidth, horizontalSeparator, outputLineBuilder);

        for (int i = 0; i < data.length; i++) {
            /*отрисовка левого индексного столбца*/
            outputLineBuilder.append(String.format("%" + firstColumnWidth + "d", i + 1));
            outputLineBuilder.append(VERTICAL_BORDER);

            /*Отрисовка самой таблицы умножения из заранее сгенерированной матрицы*/
            for (int j = 0; j < data.length; j++) {
                outputLineBuilder.append(String.format("%" + standardColumnWidth + "d", data[i][j]));

                if (j != size - 1) {
                    outputLineBuilder.append(VERTICAL_BORDER);
                }
            }
            outputLineBuilder.append(String.format("\n%" + (standardColumnWidth + 1) + "s\n", horizontalSeparator));
        }

        return outputLineBuilder.toString();
    }

    /*Отрисовка индексной строки*/
    private void drawIndexRow(int[][] data, int standardColumnWidth, String horizontalSeparator, StringBuilder outputLineBuilder) {
        for (int i = 0; i < data.length; i++) {
            outputLineBuilder.append(String.format("%" + standardColumnWidth + "d", i + 1));

            if (i != data.length - 1) {
                outputLineBuilder.append(VERTICAL_BORDER);
            } else {
                outputLineBuilder.append(String.format("\n%" + (standardColumnWidth + 1) + "s\n", horizontalSeparator));
            }
        }
    }

    /*Возвращает строковое представление горизонтального разделителя для отдельной колонки*/
    private String getColumnUnderline(int width) {
        return HORIZONTAL_BORDER.repeat(width);
    }

    /*Возвращает горизонтальный разделитель таблицы, состоящий из разделителя для первой колонки, разделителей стандартных
    колонок и перекрестий*/
    private String getHorizontalSeparator(String firstColumnUnderline, String standardColumnUnderline, int size) {
        var sb = new StringBuilder();

        sb.append(firstColumnUnderline);
        sb.append(CROSS_BORDER);

        for (int i = 0; i < size; i++) {
            sb.append(standardColumnUnderline);

            if (i < size - 1) {
                sb.append(CROSS_BORDER);
            }
        }

        return sb.toString();
    }

    /*Определяет ширину колонки, необходимую для форматирования, исходя из размера переданного числа*/
    private int getColumnWidth(int size) {
        var width = 0;
        var temp = size;

        while (temp > 0) {
            width++;
            temp /= 10;
        }

        return width;
    }
}
