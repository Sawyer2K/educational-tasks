package ru.cft.focusstart.task3.model;

import ru.cft.focusstart.task3.view.GameType;

public final class GameParametersIdentifier {

    static int getRowCountByGameType(GameType gameType) {
        return switch (gameType) {
            case NOVICE -> 9;
            case MEDIUM, EXPERT -> 16;
        };
    }

    static int getColumnCountByGameType(GameType gameType) {
        return switch (gameType) {
            case NOVICE -> 9;
            case MEDIUM -> 16;
            case EXPERT -> 30;
        };
    }

    static int getMinesCountByGameType(GameType gameType) {
        return switch (gameType) {
            case NOVICE -> 10;
            case MEDIUM -> 40;
            case EXPERT -> 99;
        };
    }
}
