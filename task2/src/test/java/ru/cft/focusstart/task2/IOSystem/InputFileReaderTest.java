package ru.cft.focusstart.task2.IOSystem;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class InputFileReaderTest {

    @Test
    @DisplayName("Тест проверяет бросание исключения FileNotFoundException при передаче неверного пути ко входному файлу.")
    public void readInputTestFirstCase() {
        assertThrows(FileNotFoundException.class, () -> {
            var inputFromCLI = "./src/test/resources/WrongFileName.txt";
            var fileReader = new InputFileReader(inputFromCLI);
            fileReader.readInput();
        }, "FileNotFoundException should have been thrown but it wasn't");
    }

    @Test
    @DisplayName("Тест проверяет, что метод возвращает не пустой лист, если передан корректный путь ко входному файлую")
    public void readInputTestSecondCase() throws FileNotFoundException {
        var inputFromCLI = "./src/test/resources/TriangleExampleIn.txt";
        var fileReader = new InputFileReader(inputFromCLI);
        var list = fileReader.readInput();

        assertFalse(list.isEmpty(), "The returned list must not be empty");
    }
}
