package ru.cft.focusstart.task2.IOSystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class OutputFileWriter implements OutputWriter {

    private static final Logger log = LoggerFactory.getLogger(OutputFileWriter.class.getName());

    private final String pathToOutputFile;

    public OutputFileWriter(String pathToOutputFile) {
        this.pathToOutputFile = pathToOutputFile;
    }

    @Override
    public void writeData(String data) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(pathToOutputFile)) {
            writer.write(data);

            log.debug("Данные успешно записаны в файл.");
        } catch (FileNotFoundException e) {
            log.error(String.format("Внимание! Не удалось произвести запись данных в файл, расположенный по пути %s.", pathToOutputFile));

            throw e;
        }
    }
}