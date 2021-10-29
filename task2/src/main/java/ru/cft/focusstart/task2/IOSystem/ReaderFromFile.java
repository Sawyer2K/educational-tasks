package ru.cft.focusstart.task2.IOSystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReaderFromFile implements IReader {
    final String pathToInputFile;

    public ReaderFromFile(String pathToInputFile) {
        this.pathToInputFile = pathToInputFile;
    }

    @Override
    public ArrayList<String> readInput() throws FileNotFoundException {
        ArrayList<String> inputData = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(pathToInputFile))) {
            while (scanner.hasNext()) {
                inputData.add(scanner.next());
            }
        } catch (FileNotFoundException e) {
            throw e;
        }

        return inputData;
    }
}