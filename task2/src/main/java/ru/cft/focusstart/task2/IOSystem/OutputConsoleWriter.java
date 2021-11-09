package ru.cft.focusstart.task2.IOSystem;

public class OutputConsoleWriter implements OutputWriter {

    @Override
    public void writeData(String data) {
        System.out.println(data);
    }
}