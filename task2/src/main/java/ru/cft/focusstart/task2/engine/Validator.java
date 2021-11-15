package ru.cft.focusstart.task2.engine;

import java.util.List;

public final class Validator {

    public static boolean ensureCorrectParamsListSize(List<Double> paramsList, int expectedParamsCount) {
        return paramsList.size() == expectedParamsCount;
    }

    public static boolean ensureCorrectParamsInList(List<Double> paramsList) {
        for (var param : paramsList) {
            if (param <= 0) {
                return false;
            }
        }

        return true;
    }
}
