package ru.cft.focusstart.task2.IOSystem;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class WriterToFileTest {

    @Test
    @DisplayName("Тест проверяет корректность записанных в файл данных.")
    public void writeDataTestCaseOne() throws IOException {
        String pathToOutputFile = "./src/test/resources/TestExampleOut.txt";
        String expected = "This text\n" +
                "must match";
        WriterToFile fileWriter = new WriterToFile(pathToOutputFile);
        fileWriter.writeData(expected);

        StringBuilder fileData = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(pathToOutputFile));
        char[] buf = new char[1024];
        int numRead;
        while ((numRead = reader.read(buf)) != -1) {
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
        }
        String actual = fileData.toString();

        assertEquals(expected, actual, "The information written to the file does not match the expected one");
    }

    @Test
    @DisplayName("Тест проверяет бросание исключения FileNotFoundException если не удалось найти или открыть выходной файл.")
    public void writeDataTestCaseTwo() {
        assertThrows(FileNotFoundException.class, () -> {
            String pathToOutputFile = "";
            String outputData = "Data for output";
            WriterToFile writer = new WriterToFile(pathToOutputFile);
            writer.writeData(outputData);
        }, "Ожидается исключение FileNotFoundException, однако оно не было брошено.");
    }
}
