package ru.cft.focusstart.task2.IOSystem;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ReaderFromFileTest {

    @Test
    @DisplayName("Test checks that FileNotFoundException has been throws when the wrong path to input file was specified")
    public void readInputTestFirstCase() {
        assertThrows(FileNotFoundException.class, () -> {
            String inputFromCLI = "./src/test/resources/WrongFileName.txt";
            ReaderFromFile fileReader = new ReaderFromFile(inputFromCLI);
            fileReader.readInput();
        }, "FileNotFoundException should have been thrown but it wasn't");
    }

    @Test
    @DisplayName("Test checks that the method does not return an empty List if the path to the input file is correct")
    public void readInputTestSecondCase() throws FileNotFoundException {
        String inputFromCLI = "./src/test/resources/TriangleExample.txt";
        ReaderFromFile fileReader = new ReaderFromFile(inputFromCLI);
        List<String> list = fileReader.readInput();

        assertFalse(list.isEmpty(), "The returned list must noy be empty");
    }
}
