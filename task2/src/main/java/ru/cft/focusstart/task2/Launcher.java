package ru.cft.focusstart.task2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cft.focusstart.task2.engine.AppDispatcher;
import ru.cft.focusstart.task2.engine.Outputter;
import ru.cft.focusstart.task2.engine.ShapeProcessor;

import java.io.FileNotFoundException;

public class Launcher {

    private static final Logger log = LoggerFactory.getLogger(Launcher.class.getName());

    public static void main(String[] args) throws FileNotFoundException {
        log.info("Старт приложения.");

        run(args);

        log.info("Успешное завершение приложения.");
    }

    public static void run(String[] args) throws FileNotFoundException {
        AppDispatcher dispatcher = new AppDispatcher();

        dispatcher.applicationConfigInit(args);
        dispatcher.shapeParamsInit();

        ShapeProcessor shapeProcessor = new ShapeProcessor();
        shapeProcessor.generateTheShape(dispatcher.getShapeParametersStorage());
        String result = shapeProcessor.getShape().getTextInfo();

        dispatcher.outputResult(result);
    }
}