package ru.cft.focusstart.task2.engine;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ShapeParametersParserTest {

    @Test
    @DisplayName("Тест проверяет корректное считываение параметров фигуры из списка входных данных.")
    public void correctParamsListParsingTest() {
        var listOfInputData = new ArrayList<String>();
        listOfInputData.add("CIRCLE");
        listOfInputData.add("5");
        var paramsStorage = ShapeParametersParser.parseListOfInputData(listOfInputData);

        var expectedShapeType = "CIRCLE";
        var expectedParamsList = new ArrayList<Double>();
        expectedParamsList.add(5.0);

        assertAll(
                () -> assertEquals(expectedShapeType, paramsStorage.getShapeType(),
                        "Тип фигуры, полученный после парсинга, не совпадает с ожидаемым."),
                () -> assertEquals(expectedParamsList, paramsStorage.getParamsList(),
                        "Список параметров фигуры, полученный после парсинга, не совпадает с ожидаемым.")
        );
    }

    @Test
    @DisplayName("Тест проверяет работу метода, если в качестве аргумента получен пустой список.")
    public void incorrectParamsListParsingTest() {
        assertThrows(IllegalArgumentException.class, () -> ShapeParametersParser.parseListOfInputData(new ArrayList<>()),
                    "В качестве аргумента передан пустой список, ожидается исключение IllegalArgumentException, " +
                "однако оно не было брошено.");
    }
}
