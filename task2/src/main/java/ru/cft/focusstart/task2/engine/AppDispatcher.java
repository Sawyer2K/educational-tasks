package ru.cft.focusstart.task2.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.cft.focusstart.task2.IOSystem.*;
import ru.cft.focusstart.task2.model.Shape;
import ru.cft.focusstart.task2.modelsFactory.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class AppDispatcher {

    private static final Logger log = LoggerFactory.getLogger(AppDispatcher.class.getName());

    private AppConfigStorage appConfigStorage;
    private Shape shape;
    private boolean outputFileExists = false;
    private List<String> listOfInputData = new ArrayList<>();

    public AppConfigStorage getAppConfigStorage() {
        return appConfigStorage;
    }

    public List<String> getListOfInputData() {
        return listOfInputData;
    }

    public Shape getShape() {
        return shape;
    }

    public void applicationConfigInit(String[] args) {
        log.debug("Старт инициализации параметров приложения.");

        var consoleConfigReader = new ConsoleConfigParser(args);
        appConfigStorage = consoleConfigReader.parseCommandLine();

        if (appConfigStorage.getPathToOutputFile() != null) {
            outputFileExists = true;
        }

        log.debug("Конец инициализации параметров приложения.");
    }

    public void defineInputData() throws FileNotFoundException {
        log.debug("Начало чтения входных данных.");

        var fileReader = new InputFileReader(appConfigStorage.getPathToInputFile());

        listOfInputData = fileReader.readInput();

        log.debug("Конец чтения входных данных.");
    }

    public void generateTheShape() {
        log.debug("Начало процесса обработки фигуры.");

        if (listOfInputData.isEmpty()) {
            throw new ArrayStoreException("Список входных данных пуст.");
        }

        ShapeFactory shapeFactory = defineConcreteShapeFactory(defineShapeType());
        shape = shapeFactory.createShape(retrieveParamsList());

        log.info("Фигура успешно создана.");
    }

    private ShapeFactory defineConcreteShapeFactory(String shapeType) {
        log.debug("Выбор конкретной \"фабрики\" для создания фигуры.");

        return switch (shapeType) {
            case "CIRCLE" -> new CircleFactory();
            case "RECTANGLE" -> new RectangleFactory();
            case "TRIANGLE" -> new TriangleFactory();
            default -> throw new IllegalArgumentException("Данный аргумент не поддреживается: " + shapeType);
        };
    }

    private String defineShapeType() {
        return listOfInputData.get(0);
    }

    private ArrayList<Double> retrieveParamsList() {
        ArrayList<Double> paramsList = new ArrayList<>();

        for (int i = 1; i < listOfInputData.size(); i++) {
            paramsList.add(Double.valueOf(listOfInputData.get(i)));
        }

        return paramsList;
    }

    public void outputResults() {
        log.debug("Старт метода для вывода результата.");

        IWriter writer;
        var outputData = shape.getTextInfo();

        if (outputFileExists) {
            writer = new OutputFileWriter(appConfigStorage.getPathToOutputFile());

            log.debug(String.format("Результат будет выведен в файл, расположенный по адресу %s", appConfigStorage.getPathToOutputFile()));
        } else {
            writer = new OutputConsoleWriter();

            log.debug("Результат будет выведен в консоль.");
        }

        try {
            writer.writeData(outputData);
        } catch (FileNotFoundException exception) {
            log.warn("Не удалось найти или открыть выходной файл.");
        }

        log.info("Результат выведен успешно.");
    }
}