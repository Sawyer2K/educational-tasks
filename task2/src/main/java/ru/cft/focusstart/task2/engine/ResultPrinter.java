package ru.cft.focusstart.task2.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cft.focusstart.task2.IOSystem.OutputWriter;
import ru.cft.focusstart.task2.IOSystem.OutputConsoleWriter;
import ru.cft.focusstart.task2.IOSystem.OutputFileWriter;

import java.io.FileNotFoundException;

public class ResultPrinter {

    private static final Logger log = LoggerFactory.getLogger(ResultPrinter.class.getName());

    private String pathToOutputFile;

    public ResultPrinter() {

    }

    public ResultPrinter(String pathToOutputFile) {
        this.pathToOutputFile = pathToOutputFile;
    }

    public void outputResults(String result) {
        log.debug("Старт метода для вывода результата.");

        OutputWriter writer;

        if (pathToOutputFile != null) {
            writer = new OutputFileWriter(pathToOutputFile);

            log.debug(String.format("Результат будет выведен в файл, расположенный по адресу %s", pathToOutputFile));
        } else {
            writer = new OutputConsoleWriter();

            log.debug("Результат будет выведен в консоль.");
        }

        try {
            writer.writeData(result);
        } catch (FileNotFoundException exception) {
            log.warn("Не удалось найти или открыть выходной файл.");
        }

        log.info("Результат выведен успешно.");
    }
}
