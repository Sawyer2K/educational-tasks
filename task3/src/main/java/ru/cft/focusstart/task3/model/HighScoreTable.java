package ru.cft.focusstart.task3.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.Scanner;

public class HighScoreTable {

    private static final File HIGH_SCORE_FILE = new File("./highScore.txt");
    private static final int NOVICE_RESULT_INDEX = 0;
    private static final int MEDIUM_RESULT_INDEX = 1;
    private static final int EXPERT_RESULT_INDEX = 2;

    private final Result[] topOneResultInEachGameType = new Result[3];

    public HighScoreTable() {
        readFile();
    }

    public Result[] getTopOneResultInEachGameType() {
        return topOneResultInEachGameType;
    }

    public boolean isNewRecord(Result currentResult) {
        int index = switch (currentResult.getGameType()) {
            case "NOVICE" -> NOVICE_RESULT_INDEX;
            case "MEDIUM" -> MEDIUM_RESULT_INDEX;
            case "EXPERT" -> EXPERT_RESULT_INDEX;
            default -> 0;
        };

        return currentResult.getSeconds() < topOneResultInEachGameType[index].getSeconds();
    }

    public void updateHighScore(Result currentResult) {
        int index = switch (currentResult.getGameType()) {
            case "NOVICE" -> NOVICE_RESULT_INDEX;
            case "MEDIUM" -> MEDIUM_RESULT_INDEX;
            case "EXPERT" -> EXPERT_RESULT_INDEX;
            default -> 0;
        };

        topOneResultInEachGameType[index] = currentResult;

        writeInFile();
    }

    private void readFile() {
        try (Scanner scanner = new Scanner(HIGH_SCORE_FILE)) {
            while (scanner.hasNextLine()) {
                Optional<Result> resultOptional = parseLine(scanner.nextLine());
                resultOptional.ifPresent(this::addResultToHighScoreList);
            }
        } catch (IOException e) {
            //log error не удалось открыть файл
        }
    }

    private Optional<Result> parseLine(String line) {
        Result result = new Result();
        Scanner scanner = new Scanner(line);

        if (scanner.hasNext()) {
            result.setPlayerName(scanner.next());
        } else {
            return Optional.empty();
        }

        if (scanner.hasNext()) {
            result.setGameType(scanner.next());
        } else {
            return Optional.empty();
        }

        if (scanner.hasNext()) {
            result.setSeconds(scanner.nextInt());
        } else {
            return Optional.empty();
        }

        return Optional.of(result);
    }

    private void addResultToHighScoreList(Result result) {
        int index = switch (result.getGameType()) {
            case "NOVICE" -> NOVICE_RESULT_INDEX;
            case "MEDIUM" -> MEDIUM_RESULT_INDEX;
            case "EXPERT" -> EXPERT_RESULT_INDEX;
            default -> 0;
        };

        topOneResultInEachGameType[index] = result;
    }

    private void writeInFile() {
        try (FileOutputStream fileOutputStream = new FileOutputStream(HIGH_SCORE_FILE)) {
            StringBuilder results = new StringBuilder();

            for (Result result : topOneResultInEachGameType) {
                if (result != null) {
                    results.append(convertToString(result));
                }
            }

            fileOutputStream.write(results.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            //log error не удалось открыть файл
        }
    }

    private String convertToString(Result result) {
        return result.getPlayerName() + " " + result.getGameType() + " " + result.getSeconds() + " " + System.lineSeparator();
    }
}
