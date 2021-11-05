package ru.cft.focusstart.task2.engine;

import ru.cft.focusstart.task2.IOSystem.*;
import ru.cft.focusstart.task2.model.Shape;
import ru.cft.focusstart.task2.modelsFactory.CircleFactory;
import ru.cft.focusstart.task2.modelsFactory.RectangleFactory;
import ru.cft.focusstart.task2.modelsFactory.ShapeFactory;
import ru.cft.focusstart.task2.modelsFactory.TriangleFactory;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class AppDispatcher {

    private AppConfigStorage appConfigStorage;
    private Shape shape;
    private boolean outputFileExists = false;
    private List<String> listOfInputData;

    public void applicationConfigInit(String[] args) {
        var consoleConfigReader = new ConsoleConfigReader(args);
        appConfigStorage = consoleConfigReader.readCommandLine();

        if (appConfigStorage.getPathToOutputFile() != null) {
            outputFileExists = true;
        }
    }

    public void defineInputData() throws FileNotFoundException {
        var fileReader = new ReaderFromFile(appConfigStorage.getPathToInputFile());

        listOfInputData = fileReader.readInput();
    }

    public void processShape() {
        if (listOfInputData.isEmpty()) {
            throw new ArrayStoreException("The List of input data received from the FileReader is empty");
        }

        var shapeType = String.valueOf(listOfInputData.get(0));
        ArrayList<Double> paramsList = new ArrayList<>();

        for (int i = 1; i < listOfInputData.size(); i++) {
            paramsList.add(Double.valueOf(listOfInputData.get(i)));
        }

        ShapeFactory shapeFactory = defineConcreteShapeFactory(shapeType);
        shape = shapeFactory.createShape(paramsList);
    }

    private ShapeFactory defineConcreteShapeFactory(String shapeType) {
        return switch (shapeType) {
            case "CIRCLE" -> new CircleFactory();
            case "RECTANGLE" -> new RectangleFactory();
            case "TRIANGLE" -> new TriangleFactory();
            default -> throw new IllegalArgumentException("Unsupported argument: " + shapeType);
        };
    }

    public void outputResults() {
        IWriter writer;
        var outputData = shape.getTextInfo();

        if (outputFileExists) {
            writer = new WriterToFile(appConfigStorage.getPathToOutputFile(), outputData);
        } else {
            writer = new WriterToConsole(outputData);
        }

        try {
            writer.writeData();
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
    }
}