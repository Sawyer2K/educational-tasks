package ru.cft.focusstart.task2.engine;

import ru.cft.focusstart.task2.model.Rectangle;
import ru.cft.focusstart.task2.model.Shape;

import java.util.List;

public class RectangleFactory implements ShapeFactory {

    @Override
    public Shape createShape(List<Double> paramsList) {
        if (paramsList.size() != 2) {
            throw new IllegalArgumentException("The parameter's List does not match the expected size");
        }

        for (double param : paramsList) {
            if (param < 1) {
                throw new IllegalArgumentException("Parameters passed to the shape are invalid");
            }
        }

        var shapeName = "Прямоугольник";
        var length = paramsList.get(0);
        var width = paramsList.get(1);

        return new Rectangle(shapeName, length, width);
    }
}
