package ru.cft.focusstart.task2.IOSystem;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public interface IReader {

    ArrayList<String> readInput() throws FileNotFoundException;
}