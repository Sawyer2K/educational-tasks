package ru.cft.focusstart.task2.IOSystem;

public class OutputConsoleWriter implements IWriter {

    @Override
    public void writeData(String data) {
        System.out.println(data);
    }
}