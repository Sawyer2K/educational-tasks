package ru.cft.focusstart.task2.modelsFactory;

import ru.cft.focusstart.task2.model.Rectangle;
import ru.cft.focusstart.task2.model.Shape;

import java.util.List;

public class RectangleFactory implements ShapeFactory {

    @Override
    public Shape createShape(List<Double> paramsList) {
        if (paramsList.size() != 2) {
            throw new IllegalArgumentException("Получен неверный лист параметров. Лист параметров для прямоугольника " +
                    "должен содержать два значения - размеры сторон.");
        }

        for (double param : paramsList) {
            if (param < 1) {
                throw new IllegalArgumentException("Ошибка! Один из полученных параметров невалиден. " +
                        "Параметры должны быть ненулевыми положительными значениями");
            }
        }

        var shapeName = "Прямоугольник";
        var length = paramsList.get(0);
        var width = paramsList.get(1);

        return new Rectangle(shapeName, length, width);
    }
}
