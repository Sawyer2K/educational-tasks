package ru.cft.focusstart.task2.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ShapeParametersParser {

    private static final Logger log = LoggerFactory.getLogger(ShapeParametersParser.class.getName());

    public static ShapeParametersStorage parseListOfInputData(List<String> listOfInputData) {
        log.debug("Начало парсинга параметров фигуры.");

        if (listOfInputData.isEmpty()) {
            throw new IllegalArgumentException("Список входных данных пуст.");
        }

        var shapeParamsStorage = new ShapeParametersStorage();

        shapeParamsStorage.setShapeType(listOfInputData.get(0));

        var paramsList = new ArrayList<Double>();
        for (int i = 1; i < listOfInputData.size(); i++) {
            paramsList.add(Double.valueOf(listOfInputData.get(i)));
        }
        shapeParamsStorage.setParamsList(paramsList);

        log.debug("Успешное окончание парсинга параметров фигуры.");
        return shapeParamsStorage;
    }
}
