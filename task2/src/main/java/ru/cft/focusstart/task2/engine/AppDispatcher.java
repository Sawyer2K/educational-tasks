package ru.cft.focusstart.task2.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.cft.focusstart.task2.IOSystem.*;
import java.io.FileNotFoundException;
import java.util.List;

public class AppDispatcher {

    private static final Logger log = LoggerFactory.getLogger(AppDispatcher.class.getName());

    private AppConfigStorage appConfigStorage;
    private ShapeParametersStorage shapeParametersStorage;

    public ShapeParametersStorage getShapeParametersStorage() {
        return shapeParametersStorage;
    }

    public void applicationConfigInit(String[] args) {
        log.debug("Старт инициализации параметров приложения.");

        appConfigStorage = ConsoleConfigParser.parseCommandLine(args);

        log.debug("Конец инициализации параметров приложения.");
    }

    public void shapeParamsInit() throws FileNotFoundException {
        log.debug("Начало инициализации параметров фигуры.");

        var fileReader = new InputFileReader(appConfigStorage.getPathToInputFile());

        List<String> listOfInputData = fileReader.readInput();

        shapeParametersStorage = ShapeParametersParser.parseListOfInputData(listOfInputData);

        log.debug("Конец инициализации парамтеров фигуры.");
    }

    public void outputResult(String result) {
        Outputter outputter;

        if (appConfigStorage.getPathToOutputFile() != null) {
            outputter = new Outputter(appConfigStorage.getPathToOutputFile());
        } else {
            outputter = new Outputter();
        }

        outputter.outputResults(result);
    }
}