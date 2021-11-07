package ru.cft.focusstart.task2.IOSystem;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WriterToConsoleTest {

    @Test
    @DisplayName("Тест проверяет корректность информации выведенной в консоль.")
    public void writeData() {
        String expectedMessage = "This message";
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        WriterToConsole writer = new WriterToConsole();
        writer.writeData("This message");

        String actualMessage = output.toString().trim();


        assertEquals(expectedMessage, actualMessage,
                "Информация, выведенная в консоль, не соответствует ожидаемой.");
    }
}
