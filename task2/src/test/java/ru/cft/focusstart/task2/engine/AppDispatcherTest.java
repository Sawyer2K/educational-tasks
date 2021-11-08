package ru.cft.focusstart.task2.engine;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.cft.focusstart.task2.model.Circle;
import ru.cft.focusstart.task2.modelsFactory.CircleFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AppDispatcherTest {

    @Test
    @DisplayName("Тест проверяет корректность инициализации параметров приложения.")
    public void appConfigCorrectInitTest() {
        String[] cmdLineArgs = {"-file", "./src/test/resources/InputFileName.txt",
                "-out", "./src/test/resources/OutputFileName.txt"};
        AppDispatcher appDispatcher = new AppDispatcher();
        appDispatcher.applicationConfigInit(cmdLineArgs);

        String expectedInputFileName = "./src/test/resources/InputFileName.txt";
        String expectedOutPutFileName = "./src/test/resources/OutputFileName.txt";

        assertAll(
                () -> assertEquals(expectedInputFileName, appDispatcher.getAppConfigStorage().getPathToInputFile(),
                        "Путь к входному файлу инициализирован неверно."),
                () -> assertEquals(expectedOutPutFileName, appDispatcher.getAppConfigStorage().getPathToOutputFile(),
                        "Путь к выходному файлу инициализирован неверно."));
    }

    @Test
    @DisplayName("Тест проверяет корректность определения входных данных.")
    public void correctDefineInputDataTest() throws FileNotFoundException {
        var cmdLineArgs = new String[]{"-file", "./src/test/resources/TriangleExampleIn.txt",
                "-out", "./src/test/resources/TriangleExampleOut.txt"};
        var appDispatcher = new AppDispatcher();
        appDispatcher.applicationConfigInit(cmdLineArgs);

        appDispatcher.defineInputData();

        var expectedList = List.of("TRIANGLE", "3", "4", "5");

        assertEquals(expectedList, appDispatcher.getListOfInputData(),
                "Входные данные определены неверно.");

    }

    @Test
    @DisplayName("Тест проверяет корректность создания фигуры при условии передачи корректных параметров на примере круга.")
    public void correctGenerateTheShapeTest() throws FileNotFoundException {
        var cmdLineArgs = new String[]{"-file", "./src/test/resources/CircleExampleIn.txt",
                "-out", "./src/test/resources/CircleExampleOut.txt"};
        var appDispatcher = new AppDispatcher();
        appDispatcher.applicationConfigInit(cmdLineArgs);
        appDispatcher.defineInputData();
        appDispatcher.generateTheShape();

        List<Double> paramsListForExpectedShape = new ArrayList<>();
        paramsListForExpectedShape.add(5.0);
        var expectedShape = (Circle) new CircleFactory().createShape(paramsListForExpectedShape);
        var actualShape = (Circle) appDispatcher.getShape();

        assertAll(
                () -> assertEquals(expectedShape.getArea(), actualShape.getArea(),
                        "У контрольных кругов не сопадают площади."),
                () -> assertEquals(expectedShape.getName(), actualShape.getName(),
                        "У контрольных кругов не сопадают имена."),
                () -> assertEquals(expectedShape.getPerimeter(), actualShape.getPerimeter(),
                        "У контрольных кругов не сопадают периметры."),
                () -> assertEquals(expectedShape.getRadius(), actualShape.getRadius(),
                        "У контрольных кругов не сопадают радиусы."),
                () -> assertEquals(expectedShape.getDiameter(), actualShape.getDiameter(),
                        "У контрольных кругов не сопадают диаметры."));
    }

    @Test
    @DisplayName("Тест проверяет бросание ошибки ArrayStoreException если лист входных данных пуст.")
    public void throwExceptionWhenGenerateTheShapeWithoutInputDataTest() {
        assertThrows(ArrayStoreException.class, () -> new AppDispatcher().generateTheShape(),
                "Ожидается исключение ArrayStoreException, так как метод обращается к пустому листу, но исключение не было брошено.");

    }

    @Test
    @DisplayName("Тест проверяет корректный вывод данных на примере наличия выходного файла")
    public void outputResultsToConsoleTest() throws IOException {
        var cmdLineArgs = new String[]{"-file", "./src/test/resources/TriangleExampleIn.txt",
                "-out", "./src/test/resources/TriangleExampleOut.txt"};
        var appDispatcher = new AppDispatcher();
        appDispatcher.applicationConfigInit(cmdLineArgs);
        appDispatcher.defineInputData();
        appDispatcher.generateTheShape();

        String expectedCircleOutputFilePath = "./src/test/resources/TriangleOutputForTestAssert.txt";

        assertArrayEquals(Files.readAllBytes(Path.of("./src/test/resources/TriangleExampleOut.txt")),
                Files.readAllBytes(Path.of(expectedCircleOutputFilePath)));
    }
}
