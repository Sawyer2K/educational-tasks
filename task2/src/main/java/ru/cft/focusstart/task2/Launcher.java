package ru.cft.focusstart.task2;

import ru.cft.focusstart.task2.engine.AppDispatcher;

import java.io.FileNotFoundException;

public class Launcher {

    public static void main(String[] args) throws FileNotFoundException {
        run(args);
    }

    public static void run(String[] args) throws FileNotFoundException {
        AppDispatcher dispatcher = new AppDispatcher();

        dispatcher.applicationConfigInit(args);
        dispatcher.defineInputData();
        dispatcher.processShape();
        dispatcher.outputResults();
    }
}