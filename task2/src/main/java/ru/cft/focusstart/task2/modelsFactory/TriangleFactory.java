package ru.cft.focusstart.task2.modelsFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cft.focusstart.task2.model.Shape;
import ru.cft.focusstart.task2.model.Triangle;

import java.util.List;

public class TriangleFactory implements ShapeFactory {

    private static final Logger log = LoggerFactory.getLogger(TriangleFactory.class.getName());

    @Override
    public Shape createShape(List<Double> paramsList) {
        if (paramsList.size() != 3) {
            log.error("Получен неверный список параметров. Лист параметров для треугольника должен содержать три значения - размеры сторон.");

            throw new IllegalArgumentException();
        }

        for (var param : paramsList) {
            if (param <= 0) {
                log.error("Ошибка! Один из полученных параметров невалиден. Параметры должны быть ненулевыми положительными значениями");

                throw new IllegalArgumentException();
            }
        }

        var shapeName = "Треугольник";
        var sideA = paramsList.get(0);
        var sideB = paramsList.get(1);
        var sideC = paramsList.get(2);

        return new Triangle(shapeName, sideA, sideB, sideC);
    }
}
