package ru.cft.focusstart.task2.modelsFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cft.focusstart.task2.model.Rectangle;
import ru.cft.focusstart.task2.model.Shape;

import java.util.List;

public class RectangleFactory implements ShapeFactory {

    private static final Logger log = LoggerFactory.getLogger(RectangleFactory.class.getName());

    @Override
    public Shape createShape(List<Double> paramsList) {
        if (paramsList.size() != 2) {
            log.error("Получен неверный список параметров. Лист параметров для прямоугольника должен содержать два значения - размеры сторон.");

            throw new IllegalArgumentException();
        }

        for (var param : paramsList) {
            if (param <= 0) {
                log.error("Ошибка! Один из полученных параметров невалиден. Параметры должны быть ненулевыми положительными значениями");

                throw new IllegalArgumentException();
            }
        }

        var shapeName = "Прямоугольник";
        var length = paramsList.get(0);
        var width = paramsList.get(1);

        return new Rectangle(shapeName, length, width);
    }
}
