package ru.cft.focusstart.task2.modelsFactory;

import ru.cft.focusstart.task2.model.Shape;

import java.util.List;

public interface ShapeFactory {

    Shape createShape(List<Double> paramsList);
}