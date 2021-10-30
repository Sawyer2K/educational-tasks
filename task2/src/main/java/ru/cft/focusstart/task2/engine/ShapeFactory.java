package ru.cft.focusstart.task2.engine;

import ru.cft.focusstart.task2.model.*;

import java.util.ArrayList;
import java.util.List;

public class ShapeFactory {

    public static Shape createShape(List<String> listOfInputData) {
        if (listOfInputData.isEmpty()) {
            throw new ArrayStoreException("The List of input data received from the FileReader is empty");
        }

        var shapeType = String.valueOf(listOfInputData.get(0));
        ArrayList<Double> paramsList = new ArrayList<>();

        for (int i = 1; i < listOfInputData.size(); i++) {
            paramsList.add(Double.valueOf(listOfInputData.get(i)));
        }

        Shape shape = switch (shapeType) {
            case "CIRCLE" -> createCircle(paramsList);
            case "RECTANGLE" -> createRectangle(paramsList);
            case "TRIANGLE" -> createTriangle(paramsList);
            default -> throw new IllegalArgumentException("Unsupported argument: " + shapeType);
        };

        return shape;
    }

    private static Shape createCircle(ArrayList<Double> paramsList) {
        if (paramsList.size() != 1) {
            throw new IllegalArgumentException("The parameter List does not match the expected size");
        } else if (paramsList.get(0) < 1) {
            throw new IllegalArgumentException("Parameters passed to the shape are invalid");
        }

        var shapeName = "Круг";
        var radius = paramsList.get(0);

        return new Circle(shapeName, radius);
    }

    private static Shape createRectangle(ArrayList<Double> paramsList) {
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

    private static Shape createTriangle(ArrayList<Double> paramsList) {
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