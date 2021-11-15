package ru.cft.focusstart.task2.modelsFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cft.focusstart.task2.engine.Validator;
import ru.cft.focusstart.task2.model.Rectangle;
import ru.cft.focusstart.task2.model.Shape;

import java.util.List;

public class RectangleFactory implements ShapeFactory {

    private static final Logger log = LoggerFactory.getLogger(RectangleFactory.class.getName());

    private static final int PARAMETERS_COUNT = 2;

    @Override
    public Shape createShape(List<Double> paramsList) {
        if (!Validator.ensureCorrectParamsListSize(paramsList, PARAMETERS_COUNT)) {
            log.error(String.format("Ошибка! В полученном списке количество элементов - %d. Список параметров для " +
                    "прямоугольника должен содержать два значения - размеры сторон.", paramsList.size()));

            throw new IllegalArgumentException();
        }

        if (!Validator.ensureCorrectParamsInList(paramsList)) {
            log.error("Ошибка! Один из полученных параметров невалиден. Параметры должны быть ненулевыми положительными значениями.");

            throw new IllegalArgumentException();
        }


        var shapeName = "Прямоугольник";
        var length = paramsList.get(0);
        var width = paramsList.get(1);

        return new Rectangle(shapeName, length, width);
    }
}
