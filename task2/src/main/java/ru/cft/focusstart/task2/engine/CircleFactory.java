package ru.cft.focusstart.task2.engine;

import ru.cft.focusstart.task2.model.Circle;
import ru.cft.focusstart.task2.model.Shape;

import java.util.List;

public class CircleFactory implements ShapeFactory {

    @Override
    public Shape createShape(List<Double> paramsList) {
        if (paramsList.size() != 1) {
            throw new IllegalArgumentException("The parameter List does not match the expected size");
        } else if (paramsList.get(0) < 1) {
            throw new IllegalArgumentException("Parameters passed to the shape are invalid");
        }

        var shapeName = "Круг";
        var radius = paramsList.get(0);

        return new Circle(shapeName, radius);
    }
}
