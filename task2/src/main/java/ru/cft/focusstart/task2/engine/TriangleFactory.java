package ru.cft.focusstart.task2.engine;

import ru.cft.focusstart.task2.model.Shape;
import ru.cft.focusstart.task2.model.Triangle;

import java.util.List;

public class TriangleFactory implements ShapeFactory {

    @Override
    public Shape createShape(List<Double> paramsList) {
        if (paramsList.size() != 3) {
            throw new IllegalArgumentException("The parameter List does not match the expected size");
        }

        for (double param : paramsList) {
            if (param < 1) {
                throw new IllegalArgumentException("Parameters passed to the shape are invalid");
            }
        }

        var shapeName = "Треугольник";
        var sideA = paramsList.get(0);
        var sideB = paramsList.get(1);
        var sideC = paramsList.get(2);

        return new Triangle(shapeName, sideA, sideB, sideC);
    }
}
