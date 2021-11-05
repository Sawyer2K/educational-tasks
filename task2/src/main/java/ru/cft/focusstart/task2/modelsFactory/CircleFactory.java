package ru.cft.focusstart.task2.modelsFactory;

import ru.cft.focusstart.task2.model.Circle;
import ru.cft.focusstart.task2.model.Shape;

import java.util.List;

public class CircleFactory implements ShapeFactory {

    @Override
    public Shape createShape(List<Double> paramsList) {
        if (paramsList.size() != 1) {
            throw new IllegalArgumentException("Получен неверный лист параметров. Лист параметров для круга должен " +
                    "содержать только один параметр - радиус.");
        } else if (paramsList.get(0) < 1) {
            throw new IllegalArgumentException("Ошибка! Получен невалидный параметр к качестве радиуса. Параметры должны " +
                    "быть ненулевыми положительными значениями.");
        }

        var shapeName = "Круг";
        var radius = paramsList.get(0);

        return new Circle(shapeName, radius);
    }
}
