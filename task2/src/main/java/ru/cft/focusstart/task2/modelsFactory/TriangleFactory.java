package ru.cft.focusstart.task2.modelsFactory;

import ru.cft.focusstart.task2.model.Shape;
import ru.cft.focusstart.task2.model.Triangle;

import java.util.List;

public class TriangleFactory implements ShapeFactory {

    @Override
    public Shape createShape(List<Double> paramsList) {
        if (paramsList.size() != 3) {
            throw new IllegalArgumentException("Получен неверный лист параметров. Лист параметров для треугольника " +
                    "должен содержать три значения - размеры сторон.");
        }

        for (double param : paramsList) {
            if (param < 1) {
                throw new IllegalArgumentException("Ошибка! Один из полученных параметров невалиден. Параметры должны " +
                        "быть ненулевыми положительными значениями");
            }
        }

        var shapeName = "Треугольник";
        var sideA = paramsList.get(0);
        var sideB = paramsList.get(1);
        var sideC = paramsList.get(2);

        return new Triangle(shapeName, sideA, sideB, sideC);
    }
}
