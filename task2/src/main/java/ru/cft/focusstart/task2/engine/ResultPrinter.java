package ru.cft.focusstart.task2.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cft.focusstart.task2.IOSystem.FailedOutputException;
import ru.cft.focusstart.task2.IOSystem.OutputWriter;
import ru.cft.focusstart.task2.IOSystem.OutputConsoleWriter;
import ru.cft.focusstart.task2.IOSystem.OutputFileWriter;

public class ResultPrinter {

    private static final Logger log = LoggerFactory.getLogger(ResultPrinter.class.getName());

    private final String pathToOutputFile;
    private final OutputWriter writer;

    public ResultPrinter() {
        this.pathToOutputFile = null;
        writer = new OutputConsoleWriter();

        log.debug("Результат будет выведен в консоль.");
    }

    public ResultPrinter(String pathToOutputFile) {
        this.pathToOutputFile = pathToOutputFile;
        writer = new OutputFileWriter(pathToOutputFile);

        log.debug(String.format("Результат будет выведен в файл, расположенный по адресу %s", pathToOutputFile));
    }

    public void outputResults(String result) {
        log.debug("Старт метода для вывода результата.");

        try {
            writer.writeData(result);

            log.info("Результат выведен успешно.");
        } catch (FailedOutputException exception) {
            log.warn(String.format("Не удалось найти или открыть выходной файл, расположенный по адресу %s", pathToOutputFile));
        }
    }
}
