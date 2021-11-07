package ru.cft.focusstart.task2.modelsFactory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.cft.focusstart.task2.IOSystem.ReaderFromFile;
import ru.cft.focusstart.task2.model.Circle;
import ru.cft.focusstart.task2.model.Shape;

import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CircleFactoryTest {

    @Test
    @DisplayName("Test checks the correctness of creating a shape.")
    public void circleCorrectCreationTest() {
        List<Double> paramsList = new ArrayList<>();
        paramsList.add(5.0);
        Circle circle = (Circle) new CircleFactory().createShape(paramsList);

        DecimalFormat dF = new DecimalFormat("#.##");

        String expectedType = "Круг";
        String expectedArea = "78,54";
        String expectedPerimeter = "31,42";
        String expectedRadius = "5";
        String expectedDiameter = "10";

        assertAll(
                () -> assertEquals(expectedType, circle.getName(),
                        "The type was initialized incorrectly"),
                () -> assertEquals(expectedArea, dF.format(circle.getArea()),
                        "The area was calculated incorrectly"),
                () -> assertEquals(expectedPerimeter, dF.format(circle.getPerimeter()),
                        "The perimeter was calculated incorrectly"),
                () -> assertEquals(expectedRadius, dF.format(circle.getRadius()),
                        "The radius was initialized incorrectly"),
                () -> assertEquals(expectedDiameter, dF.format(circle.getDiameter()),
                        "The diameter was calculated incorrectly")
        );
    }

    @Test
    @DisplayName("Test checks that IllegalArgumentException has been throws if an incorrect parameters list was passed.")
    public void circleCreationWithIncorrectParamsListTest() {
        List<Double> paramsList = new ArrayList<>();
        paramsList.add(5.0);
        paramsList.add(10.0);

        assertThrows(IllegalArgumentException.class, () -> {
            Circle circle = (Circle) new CircleFactory().createShape(paramsList);
        }, "IllegalArgumentException should have been thrown but it wasn't");
    }

    @Test
    @DisplayName("Test checks that IllegalArgumentException has been throws if an negative number as parameter was passed.")
    public void circleCreationWithIncorrectParameterTest() {
        List<Double> paramsList = new ArrayList<>();
        paramsList.add(-5.0);

        assertThrows(IllegalArgumentException.class, () -> {
            Circle circle = (Circle) new CircleFactory().createShape(paramsList);
        }, "IllegalArgumentException should have been thrown but it wasn't");
    }

    @Test
    @DisplayName("The test checks the correctness of the output of all the necessary parameters of the shape.")
    public void getTextInfoTest() {
        String type = "Круг";
        int radius = 5;
        Shape circle = new Circle(type, radius);
        String actualMessage = circle.getTextInfo();

        String expectedMessage = """
                Тип фигуры: Круг
                Площадь: 78,54 кв. мм
                Периметр: 31,42 мм
                Радиус: 5 мм
                Диаметр: 10 мм
                """;

        assertEquals(expectedMessage, actualMessage,
                "The output text pattern does not match the expected result");
    }
}
