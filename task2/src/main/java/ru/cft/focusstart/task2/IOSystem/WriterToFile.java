package ru.cft.focusstart.task2.IOSystem;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class WriterToFile implements IWriter {

    String pathToOutputFile;
    String outputData;

    public WriterToFile(String pathToOutputFile, String outputData) {
        this.pathToOutputFile = pathToOutputFile;
        this.outputData = outputData;
    }

    @Override
    public void writeData() throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(pathToOutputFile)) {
            writer.write(outputData);
        } catch (FileNotFoundException e) {
            throw e;
        }
    }
}