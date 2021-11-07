package ru.cft.focusstart.task2.engine;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ConsoleConfigReaderTest {

    @Test
    @DisplayName("Тест проверяет правильное считывание параметров, указанных не по порядку, и их правильную инициализацию.")
    public void checkingCorrectParamsReadingAndInit() {
        AppConfigStorage configStorage = new AppConfigStorage();
        configStorage.setPathToOutputFile("path/to/input/file");
        String[] inputFromCLI = new String[]{"-out", "path/to/output/file", "-file", "path/to/input/file"};
        ConsoleConfigReader configReader = new ConsoleConfigReader(inputFromCLI);
        configReader.readCommandLine();

        String expectedIn = "path/to/input/file";
        String expectedOut = "path/to/output/file";

        assertAll(
                () -> assertEquals(expectedIn, configStorage.getPathToInputFile(),
                        "Инициализированный путь к входному файлу не соответствует ожидаемому."),
                () -> assertEquals(expectedOut, configStorage.getPathToOutputFile(),
                        "Инициализированный путь к выходному файлу не соответствует ожидаемому."));

    }

    @Test
    @DisplayName("Тест проверяет работу метода, если передаются недопустимые аргументы командной строки.")
    public void readInputTestSecondCase() {
        assertThrows(IllegalArgumentException.class, () -> {
            AppConfigStorage configStorage = new AppConfigStorage();
            configStorage.setPathToOutputFile("/path/to/input/file2");

            String[] inputFromCLI = new String[]{"out", "path/to/output/file2", "-inputFile", "path/to/input/file"};

            ConsoleConfigReader configReader = new ConsoleConfigReader(inputFromCLI);
            configReader.readCommandLine();
        }, "Переданы недопустимые аргументы, ожидается исключение IllegalArgumentException.");
    }
}

