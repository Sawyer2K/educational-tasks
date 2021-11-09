package ru.cft.focusstart.task2.modelsFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cft.focusstart.task2.model.Circle;
import ru.cft.focusstart.task2.model.Shape;

import java.util.List;

public class CircleFactory implements ShapeFactory {

    private static final Logger log = LoggerFactory.getLogger(CircleFactory.class.getName());

    @Override
    public Shape createShape(List<Double> paramsList) {
        if (paramsList.size() != 1) {
            log.error(String.format("Ошибка! В полученном списке количество элементов - %d Список параметров для круга " +
                    "должен содержать только один параметр - радиус.", paramsList.size()));

            throw new IllegalArgumentException();
        } else if (paramsList.get(0) <= 0) {
            log.error(String.format("Ошибка! Получен невалидный параметр %f к качестве радиуса. Параметры должны " +
                            "быть ненулевыми положительными значениями.",
                    paramsList.get(0)));

            throw new IllegalArgumentException();
        }

        var shapeName = "Круг";
        var radius = paramsList.get(0);

        return new Circle(shapeName, radius);
    }
}
