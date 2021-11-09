package ru.cft.focusstart.task2.IOSystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class InputFileReader implements InputReader {

    private static final Logger log = LoggerFactory.getLogger(InputFileReader.class.getName());

    private final String pathToInputFile;

    public InputFileReader(String pathToInputFile) {
        this.pathToInputFile = pathToInputFile;
    }

    @Override
    public ArrayList<String> readInput() throws FileNotFoundException {
        log.debug("Начало чтения из файла.");

        var inputData = new ArrayList<String>();

        try (var scanner = new Scanner(new File(pathToInputFile))) {
            while (scanner.hasNext()) {
                inputData.add(scanner.next());
            }
        } catch (FileNotFoundException e) {
            log.error(String.format("Ошибка! Не найден входной файл по пути %s, чтение не возможно. Работа программы остановлена.",
                    pathToInputFile));
            throw e;
        }

        log.debug("Чтение файла завершено успешно.");
        return inputData;
    }
}