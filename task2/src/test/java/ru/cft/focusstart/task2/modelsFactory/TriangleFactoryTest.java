package ru.cft.focusstart.task2.modelsFactory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.cft.focusstart.task2.model.Triangle;
import ru.cft.focusstart.task2.model.Shape;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TriangleFactoryTest {

    @Test
    @DisplayName("Test checks the correctness of creating a shape")
    public void triangleCorrectCreationTest() {
        List<Double> paramsList = List.of(3.0, 4.0, 5.0);
        Triangle triangle = (Triangle) new TriangleFactory().createShape(paramsList);

        DecimalFormat dF = new DecimalFormat("#.##");

        String expectedType = "Треугольник";
        String expectedArea = "6";
        String expectedPerimeter = "12";
        String expectedSideA = "3";
        String expectedSideB = "4";
        String expectedSideC = "5";
        String expectedAngleA = "36,87";
        String expectedAngleB = "53,13";
        String expectedAngleC = "90";

        assertAll(
                () -> assertEquals(expectedType, triangle.getName(),
                        "The type was initialized incorrectly"),
                () -> assertEquals(expectedArea, dF.format(triangle.getArea()),
                        "The area was calculated incorrectly"),
                () -> assertEquals(expectedPerimeter, dF.format(triangle.getPerimeter()),
                        "The perimeter was calculated incorrectly"),
                () -> assertEquals(expectedSideA, dF.format(triangle.getSideA()),
                        "The side A was initialized incorrectly"),
                () -> assertEquals(expectedSideB, dF.format(triangle.getSideB()),
                        "The side B was initialized incorrectly"),
                () -> assertEquals(expectedSideC, dF.format(triangle.getSideC()),
                        "The side C was initialized incorrectly"),
                () -> assertEquals(expectedAngleA, dF.format(triangle.getAngleA()),
                        "The angle opposite to side A was calculated incorrectly"),
                () -> assertEquals(expectedAngleB, dF.format(triangle.getAngleB()),
                        "The angle opposite to side B was calculated incorrectly"),
                () -> assertEquals(expectedAngleC, dF.format(triangle.getAngleC()),
                        "The angle opposite to side C was calculated incorrectly")

        );
    }

    @Test
    @DisplayName("Test checks that IllegalArgumentException has been throws if an incorrect parameters list was passed.")
    public void triangleCreationWithIncorrectParamsListTest() {
        List<Double> paramsList = new ArrayList<>();
        paramsList.add(4.0);
        paramsList.add(8.0);

        assertThrows(IllegalArgumentException.class, () -> new TriangleFactory().createShape(paramsList),
                "IllegalArgumentException should have been thrown but it wasn't");
    }

    @Test
    @DisplayName("Test checks that IllegalArgumentException has been throws if an negative number as one of parameter was passed.")
    public void triangleCreationWithIncorrectParameterTest() {
        List<Double> paramsList = new ArrayList<>();
        paramsList.add(-2.0);
        paramsList.add(3.0);
        paramsList.add(4.0);

        assertThrows(IllegalArgumentException.class, () -> new TriangleFactory().createShape(paramsList),
                "IllegalArgumentException should have been thrown but it wasn't");
    }

    @Test
    @DisplayName("The test checks the correctness of the output of all the necessary parameters of the shape")
    public void getTextInfoTest() {
        List<Double> paramsList = List.of(3.0, 4.0, 5.0);
        Triangle triangle = (Triangle) new TriangleFactory().createShape(paramsList);

        String actualMessage = triangle.getTextInfo();
        String expectedMessage = """
                Тип фигуры: Треугольник
                Площадь: 6 кв. мм
                Периметр: 12 мм
                Сторона A: 3 мм, противолежащий угол: 36,87 градусов
                Сторона B: 4 мм, противолежащий угол: 53,13 градусов
                Сторона C: 5 мм, противолежащий угол: 90 градусов
                """;

        assertEquals(expectedMessage, actualMessage,
                "The output text pattern does not match the expected result");
    }
}
