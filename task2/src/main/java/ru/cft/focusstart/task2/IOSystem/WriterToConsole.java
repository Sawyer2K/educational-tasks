package ru.cft.focusstart.task2.IOSystem;

public class WriterToConsole implements IWriter {

    String outputData;

    public WriterToConsole(String outputData) {
        this.outputData = outputData;
    }

    @Override
    public void writeData() {
        System.out.println(outputData);
    }
}