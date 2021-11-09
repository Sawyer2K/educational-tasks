package ru.cft.focusstart.task2.IOSystem;

import java.io.FileNotFoundException;

public interface OutputWriter {

    void writeData(String data) throws FileNotFoundException;
}
