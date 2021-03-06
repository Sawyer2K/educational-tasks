package ru.cft.focusstart.task2.engine;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ConsoleConfigParserTest {

    @Test
    @DisplayName("Тест проверяет корректное считывание параметров, указанных не по порядку, и их правильную инициализацию.")
    public void correctConfigsParsingTest() {
        var inputFromCLI = new String[]{"-out", "path/to/output/file", "-file", "path/to/input/file"};
        AppConfigStorage configStorage = ConsoleConfigParser.parseCommandLine(inputFromCLI);

        var expectedIn = "path/to/input/file";
        var expectedOut = "path/to/output/file";

        assertAll(
                () -> assertEquals(expectedIn, configStorage.getPathToInputFile(),
                        "Инициализированный путь к входному файлу не соответствует ожидаемому."),
                () -> assertEquals(expectedOut, configStorage.getPathToOutputFile(),
                        "Инициализированный путь к выходному файлу не соответствует ожидаемому."));

    }

    @Test
    @DisplayName("Тест проверяет работу метода, если передаются недопустимые аргументы командной строки.")
    public void incorrectConfigsParsingTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            var inputFromCLI = new String[]{"out", "path/to/output/file2", "-inputFile", "path/to/input/file"};

            ConsoleConfigParser.parseCommandLine(inputFromCLI);
        }, "Переданы недопустимые аргументы, ожидается исключение IllegalArgumentException.");
    }

    @Test
    @DisplayName("Тест проверяет работу метода, если передаются дублирующиеся аргументы.")
    public void incorrectConfigsParsingWhenDuplicateArgumentsPassedTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            var inputFromCLI = new String[]{"-out", "path/to/output/file2", "-inputFile", "path/to/input/file", "-out", "path/to/output/file3"};

            ConsoleConfigParser.parseCommandLine(inputFromCLI);
        }, "Один из переданных аргументов дублируется, ожидается исключение IllegalArgumentException.");
    }
}

