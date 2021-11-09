package ru.cft.focusstart.task2.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cft.focusstart.task2.model.Shape;
import ru.cft.focusstart.task2.modelsFactory.CircleFactory;
import ru.cft.focusstart.task2.modelsFactory.RectangleFactory;
import ru.cft.focusstart.task2.modelsFactory.ShapeFactory;
import ru.cft.focusstart.task2.modelsFactory.TriangleFactory;

public class ShapeGenerator {

    private static final Logger log = LoggerFactory.getLogger(ShapeGenerator.class.getName());

    Shape shape;

    public Shape getShape() {
        return shape;
    }

    public void generateTheShape(ShapeParametersStorage shapeParametersStorage) {
        log.debug("Начало процесса создания фигуры.");

        ShapeFactory shapeFactory = getConcreteShapeFactory(shapeParametersStorage.getShapeType());
        shape = shapeFactory.createShape(shapeParametersStorage.getParamsList());

        log.info("Фигура успешно создана.");
    }

    private ShapeFactory getConcreteShapeFactory(String shapeType) {
        log.debug("Выбор конкретной \"фабрики\" для создания фигуры.");

        return switch (shapeType) {
            case "CIRCLE" -> new CircleFactory();
            case "RECTANGLE" -> new RectangleFactory();
            case "TRIANGLE" -> new TriangleFactory();
            default -> throw new IllegalArgumentException("Данный аргумент не поддерживается: " + shapeType);
        };
    }
}
