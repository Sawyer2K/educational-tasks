package ru.cft.focusstart.task2.engine;

import ru.cft.focusstart.task2.IOSystem.*;
import ru.cft.focusstart.task2.model.Shape;

import java.io.FileNotFoundException;
import java.util.List;

public class Launcher {
    private String pathToInputFile;
    private String pathToOutputFile;
    private boolean outputFileExists = false;
    private Shape shape;

    public String getPathToInputFile() {
        return pathToInputFile;
    }

    public String getPathToOutputFile() {
        return pathToOutputFile;
    }

    public boolean isOutputFileExists() {
        return outputFileExists;
    }

    public void applicationParamsInit(String[] args) {
        var consoleConfigReader = new ConsoleConfigReader(args);
        var applicationConfigStorage = consoleConfigReader.readInput();

        pathToInputFile = applicationConfigStorage.getPathToInputFile();

        if (applicationConfigStorage.getPathToOutputFile() != null) {
            pathToOutputFile = applicationConfigStorage.getPathToOutputFile();
            outputFileExists = true;
        }
    }

    public List<String> defineInputData() throws FileNotFoundException {
        var fileReader = new ReaderFromFile(pathToInputFile);

        return fileReader.readInput();
    }

    public void processShape() throws FileNotFoundException {
        shape = ShapeFactory.createShape(defineInputData());
    }

    public void outputResults() {
        IWriter writer;
        var outputData = shape.getTextInfo();

        if (outputFileExists) {
            writer = new WriterToFile(pathToOutputFile, outputData);
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