package ru.cft.focusstart.task2.modelsFactory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.cft.focusstart.task2.model.Circle;
import ru.cft.focusstart.task2.model.Shape;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CircleFactoryTest {

    @Test
    @DisplayName("Тест проверяет корректность создания круга.")
    public void circleCorrectCreationTest() {
        List<Double> paramsList = new ArrayList<>();
        paramsList.add(5.0);
        var circle = (Circle) new CircleFactory().createShape(paramsList);

        var dF = new DecimalFormat("#.##");

        var expectedType = "Круг";
        var expectedArea = "78,54";
        var expectedPerimeter = "31,42";
        var expectedRadius = "5";
        var expectedDiameter = "10";

        assertAll(
                () -> assertEquals(expectedType, circle.getName(),
                        "Название фигуры было инициализированно неверно."),
                () -> assertEquals(expectedArea, dF.format(circle.getArea()),
                        "Площадь фигуры была инициализированна неверно."),
                () -> assertEquals(expectedPerimeter, dF.format(circle.getPerimeter()),
                        "Периметр фигуры был инициализированн неверно."),
                () -> assertEquals(expectedRadius, dF.format(circle.getRadius()),
                        "Радиус фигуры был инициализированн неверно."),
                () -> assertEquals(expectedDiameter, dF.format(circle.getDiameter()),
                        "Название диаметр был инициализированн неверно.")
        );
    }

    @Test
    @DisplayName("Тест проверяет бросание исключения IllegalArgumentException, если передан некорректный лист параметров.")
    public void circleCreationWithIncorrectParamsListTest() {
        List<Double> paramsList = new ArrayList<>();
        paramsList.add(5.0);
        paramsList.add(10.0);

        assertThrows(IllegalArgumentException.class, () -> new CircleFactory().createShape(paramsList),
            "IllegalArgumentException ожидался, но не был брошен.");
    }

    @Test
    @DisplayName("Тест проверяет бросание исключения IllegalArgumentException, если в качестве параметра передано отрицательное число.")
    public void circleCreationWithIncorrectParameterTest() {
        List<Double> paramsList = new ArrayList<>();
        paramsList.add(-5.0);

        assertThrows(IllegalArgumentException.class, () -> new CircleFactory().createShape(paramsList),
                "IllegalArgumentException ожидался, но не был брошен.");
    }

    @Test
    @DisplayName("Тест проверяет работу метода, выводящего информацию о фигуре.")
    public void getTextInfoTest() {
        var type = "Круг";
        var radius = 5;
        var circle = new Circle(type, radius);
        var actualMessage = circle.getTextInfo();

        var expectedMessage = """
                Тип фигуры: Круг
                Площадь: 78,54 кв. мм
                Периметр: 31,42 мм
                Радиус: 5 мм
                Диаметр: 10 мм
                """;

        assertEquals(expectedMessage, actualMessage,
                "Паттерн выведенной информации не соответствует ожидаемой.");
    }
}
