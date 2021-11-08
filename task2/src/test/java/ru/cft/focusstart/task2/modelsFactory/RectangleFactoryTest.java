package ru.cft.focusstart.task2.modelsFactory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.cft.focusstart.task2.model.Rectangle;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RectangleFactoryTest {

    @Test
    @DisplayName("Тест проверяет корректность создания прямоугольника")
    public void rectangleCorrectCreationTest() {
        List<Double> paramsList = List.of(8.0, 6.0);
        var rectangle = (Rectangle) new RectangleFactory().createShape(paramsList);

        var dF = new DecimalFormat("#.##");

        var expectedType = "Прямоугольник";
        var expectedArea = "48";
        var expectedPerimeter = "28";
        var expectedDiagonal = "10";
        var expectedLength = "8";
        var expectedWidth = "6";

        assertAll(
                () -> assertEquals(expectedType, rectangle.getName(),
                        "Название фигуры было инициализированно неверно."),
                () -> assertEquals(expectedArea, dF.format(rectangle.getArea()),
                        "Площадь фигуры была инициализированна неверно."),
                () -> assertEquals(expectedPerimeter, dF.format(rectangle.getPerimeter()),
                        "Периметр фигуры был инициализированн неверно."),
                () -> assertEquals(expectedDiagonal, dF.format(rectangle.getDiagonal()),
                        "Диагональ фигуры была инициализированная неверно."),
                () -> assertEquals(expectedLength, dF.format(rectangle.getLength()),
                        "Длина фигуры была определена неверно."),
                () -> assertEquals(expectedWidth, dF.format(rectangle.getWidth()),
                        "Ширина фигуры была определена неверно.")
        );
    }

    @Test
    @DisplayName("Тест проверяет бросание исключения IllegalArgumentException, если передан некорректный лист параметров.")
    public void rectangleCreationWithIncorrectParamsListTest() {
        List<Double> paramsList = new ArrayList<>();
        paramsList.add(5.0);
        paramsList.add(10.0);
        paramsList.add(13.0);
        paramsList.add(1.0);

        assertThrows(IllegalArgumentException.class, () -> new RectangleFactory().createShape(paramsList),
                "IllegalArgumentException ожидался, но не был брошен.");
    }

    @Test
    @DisplayName("Тест проверяет бросание исключения IllegalArgumentException, если в качестве параметра передано отрицательное число.")
    public void rectangleCreationWithIncorrectParameterTest() {
        List<Double> paramsList = new ArrayList<>();
        paramsList.add(-6.0);
        paramsList.add(10.0);

        assertThrows(IllegalArgumentException.class, () -> new RectangleFactory().createShape(paramsList),
                "IllegalArgumentException ожидался, но не был брошен.");
    }


    @Test
    @DisplayName("Тест проверяет работу метода, выводящего информацию о фигуре.")
    public void getTextInfoTest() {
        List<Double> paramsList = List.of(8.0, 6.0);
        var rectangle = (Rectangle) new RectangleFactory().createShape(paramsList);

        var actualMessage = rectangle.getTextInfo();
        var expectedMessage = """
                Тип фигуры: Прямоугольник
                Площадь: 48 кв. мм
                Периметр: 28 мм
                Длина диагонали: 10 мм
                Длина: 8 мм
                Ширина: 6 мм
                """;

        assertEquals(expectedMessage, actualMessage,
                "Паттерн выведенной информации не соответствует ожидаемой.");
    }
}
