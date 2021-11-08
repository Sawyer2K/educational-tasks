package ru.cft.focusstart.task2.modelsFactory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.cft.focusstart.task2.model.Triangle;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TriangleFactoryTest {

    @Test
    @DisplayName("Тест проверяет корректность создания треугольника.")
    public void triangleCorrectCreationTest() {
        List<Double> paramsList = List.of(3.0, 4.0, 5.0);
        var triangle = (Triangle) new TriangleFactory().createShape(paramsList);

        var dF = new DecimalFormat("#.##");

        var expectedType = "Треугольник";
        var expectedArea = "6";
        var expectedPerimeter = "12";
        var expectedSideA = "3";
        var expectedSideB = "4";
        var expectedSideC = "5";
        var expectedAngleA = "36,87";
        var expectedAngleB = "53,13";
        var expectedAngleC = "90";

        assertAll(
                () -> assertEquals(expectedType, triangle.getName(),
                        "Название фигуры было инициализированно неверно."),
                () -> assertEquals(expectedArea, dF.format(triangle.getArea()),
                        "Площадь фигуры была инициализированна неверно."),
                () -> assertEquals(expectedPerimeter, dF.format(triangle.getPerimeter()),
                        "Периметр фигуры был инициализированн неверно."),
                () -> assertEquals(expectedSideA, dF.format(triangle.getSideA()),
                        "Сторона А была инициализированна неверно."),
                () -> assertEquals(expectedSideB, dF.format(triangle.getSideB()),
                        "Сторона В была инициализированна неверно."),
                () -> assertEquals(expectedSideC, dF.format(triangle.getSideC()),
                        "Сторона С была инициализированна неверно."),
                () -> assertEquals(expectedAngleA, dF.format(triangle.getAngleA()),
                        "Угол, лежащий напротив стороны А был подсчитан неверно."),
                () -> assertEquals(expectedAngleB, dF.format(triangle.getAngleB()),
                        "Угол, лежащий напротив стороны В был подсчитан неверно."),
                () -> assertEquals(expectedAngleC, dF.format(triangle.getAngleC()),
                        "Угол, лежащий напротив стороны С был подсчитан неверно.")

        );
    }

    @Test
    @DisplayName("Тест проверяет бросание исключения IllegalArgumentException, если передан некорректный лист параметров.")
    public void triangleCreationWithIncorrectParamsListTest() {
        List<Double> paramsList = new ArrayList<>();
        paramsList.add(4.0);
        paramsList.add(8.0);

        assertThrows(IllegalArgumentException.class, () -> new TriangleFactory().createShape(paramsList),
                "IllegalArgumentException ожидался, но не был брошен.");
    }

    @Test
    @DisplayName("Тест проверяет бросание исключения IllegalArgumentException, если в качестве параметра передано отрицательное число.")
    public void triangleCreationWithIncorrectParameterTest() {
        List<Double> paramsList = new ArrayList<>();
        paramsList.add(-2.0);
        paramsList.add(3.0);
        paramsList.add(4.0);

        assertThrows(IllegalArgumentException.class, () -> new TriangleFactory().createShape(paramsList),
                "IllegalArgumentException ожидался, но не был брошен.");
    }

    @Test
    @DisplayName("Тест проверяет работу метода, выводящего информацию о фигуре.")
    public void getTextInfoTest() {
        List<Double> paramsList = List.of(3.0, 4.0, 5.0);
        var triangle = (Triangle) new TriangleFactory().createShape(paramsList);

        var actualMessage = triangle.getTextInfo();
        var expectedMessage = """
                Тип фигуры: Треугольник
                Площадь: 6 кв. мм
                Периметр: 12 мм
                Сторона A: 3 мм, противолежащий угол: 36,87 градусов
                Сторона B: 4 мм, противолежащий угол: 53,13 градусов
                Сторона C: 5 мм, противолежащий угол: 90 градусов
                """;

        assertEquals(expectedMessage, actualMessage,
                "Паттерн выведенной информации не соответствует ожидаемой.");
    }
}
