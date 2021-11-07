package ru.cft.focusstart.task2.modelsFactory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.cft.focusstart.task2.model.Rectangle;
import ru.cft.focusstart.task2.model.Shape;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RectangleFactoryTest {

    @Test
    @DisplayName("Test checks the correctness of creating a shape")
    public void rectangleCorrectCreationTest() {
        List<Double> paramsList = List.of(8.0, 6.0);
        Rectangle rectangle = (Rectangle) new RectangleFactory().createShape(paramsList);

        DecimalFormat dF = new DecimalFormat("#.##");

        String expectedType = "Прямоугольник";
        String expectedArea = "48";
        String expectedPerimeter = "28";
        String expectedDiagonal = "10";
        String expectedLength = "8";
        String expectedWidth = "6";

        assertAll(
                () -> assertEquals(expectedType, rectangle.getName(),
                        "The type was initialized incorrectly"),
                () -> assertEquals(expectedArea, dF.format(rectangle.getArea()),
                        "The area was calculated incorrectly"),
                () -> assertEquals(expectedPerimeter, dF.format(rectangle.getPerimeter()),
                        "The perimeter was calculated incorrectly"),
                () -> assertEquals(expectedDiagonal, dF.format(rectangle.getDiagonal()),
                        "The diagonal was calculated incorrectly"),
                () -> assertEquals(expectedLength, dF.format(rectangle.getLength()),
                        "The length was defined incorrectly"),
                () -> assertEquals(expectedWidth, dF.format(rectangle.getWidth()),
                        "The width was defined incorrectly")
        );
    }

    @Test
    @DisplayName("Test checks that IllegalArgumentException has been throws if an incorrect parameters list was passed.")
    public void rectangleCreationWithIncorrectParamsListTest() {
        List<Double> paramsList = new ArrayList<>();
        paramsList.add(5.0);
        paramsList.add(10.0);
        paramsList.add(13.0);
        paramsList.add(1.0);

        assertThrows(IllegalArgumentException.class, () -> new RectangleFactory().createShape(paramsList),
                "IllegalArgumentException should have been thrown but it wasn't");
    }

    @Test
    @DisplayName("Test checks that IllegalArgumentException has been throws if an negative number as one of parameter was passed.")
    public void rectangleCreationWithIncorrectParameterTest() {
        List<Double> paramsList = new ArrayList<>();
        paramsList.add(-6.0);
        paramsList.add(10.0);

        assertThrows(IllegalArgumentException.class, () -> new RectangleFactory().createShape(paramsList),
                "IllegalArgumentException should have been thrown but it wasn't");
    }


    @Test
    @DisplayName("The test checks the correctness of the output of all the necessary parameters of the shape")
    public void getTextInfoTest() {
        List<Double> paramsList = List.of(8.0, 6.0);
        Rectangle rectangle = (Rectangle) new RectangleFactory().createShape(paramsList);

        String actualMessage = rectangle.getTextInfo();
        String expectedMessage = """
                Тип фигуры: Прямоугольник
                Площадь: 48 кв. мм
                Периметр: 28 мм
                Длина диагонали: 10 мм
                Длина: 8 мм
                Ширина: 6 мм
                """;

        assertEquals(expectedMessage, actualMessage,
                "The output text pattern does not match the expected result");
    }
}
