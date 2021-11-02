package ru.cft.focusstart.task2;

import ru.cft.focusstart.task2.engine.AppDispatcher;
import java.io.IOException;

public class Launcher {

    public static void main(String[] args) throws IOException {
        AppDispatcher dispatcher = new AppDispatcher();

        dispatcher.applicationParamsInit(args);
        dispatcher.processShape();
        dispatcher.outputResults();
    }
}