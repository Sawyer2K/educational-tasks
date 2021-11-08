package ru.cft.focusstart.task2.IOSystem;

import java.io.FileNotFoundException;

public interface IWriter {

    void writeData(String data) throws FileNotFoundException;
}
