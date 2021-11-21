package ru.cft.focusstart.task2.IOSystem;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class OutputFileWriterTest {

    @Test
    @DisplayName("Тест проверяет корректность записанных в файл данных.")
    public void writeDataTestCaseOne() throws IOException {
        var pathToOutputFile = "./src/test/resources/TestExampleOut.txt";
        var expected = "This text\n" +
                "must match";
        var fileWriter = new OutputFileWriter(pathToOutputFile);
        fileWriter.writeData(expected);

        var fileDataBuilder = new StringBuilder();
        var reader = new BufferedReader(new FileReader(pathToOutputFile));
        char[] buf = new char[1024];
        int numRead;
        while ((numRead = reader.read(buf)) != -1) {
            String readData = String.valueOf(buf, 0, numRead);
            fileDataBuilder.append(readData);
        }
        var actual = fileDataBuilder.toString();

        assertEquals(expected, actual, "Данные, записанный в файл, не соответствуют ожидаемым.");
    }

    @Test
    @DisplayName("Тест проверяет бросание исключения FailedOutputException если не удалось найти или открыть выходной файл.")
    public void writeDataTestCaseTwo() {
        assertThrows(FailedOutputException.class, () -> {
            var pathToOutputFile = "";
            var outputData = "Data for output";
            var writer = new OutputFileWriter(pathToOutputFile);
            writer.writeData(outputData);
        }, "Ожидается исключение FailedOutputException, однако оно не было брошено.");
    }
}
