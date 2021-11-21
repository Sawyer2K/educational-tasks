package ru.cft.focusstart.task2.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsoleConfigParser {

    private static final Logger log = LoggerFactory.getLogger(ConsoleConfigParser.class.getName());

    public static AppConfigStorage parseCommandLine(String[] commandLineArgs) {
        log.debug("Начало парсинга аргументов командной строки.");

        var configStorage = new AppConfigStorage();

        if (commandLineArgs.length % 2 != 0) {
            log.error("Ошибка! Список аргументов командной строки содержит нечётное количество элементов! " +
                    "Работа программы остановлена.");
            throw new IllegalArgumentException();
        }

        boolean isInputFileAlreadySpecify = false;
        boolean isOutputFileAlreadySpecify = false;

        for (int i = 0; i <= commandLineArgs.length - 2; i += 2) {
            switch (commandLineArgs[i]) {
                case "-file" -> {
                    if (!isInputFileAlreadySpecify) {
                        isInputFileAlreadySpecify = true;
                        configStorage.setPathToInputFile(commandLineArgs[i + 1]);
                    } else {
                        log.error(String.format("Ошибка! Флаг %s уже был передан в качестве аргумента коммандной строки, " +
                                "а путь ко входному файлу был проинициализирован!", commandLineArgs[i]));
                        throw new IllegalArgumentException();
                    }
                }
                case "-out" -> {
                    if (!isOutputFileAlreadySpecify) {
                        isOutputFileAlreadySpecify = true;
                        configStorage.setPathToOutputFile(commandLineArgs[i + 1]);
                    } else {
                        log.error(String.format("Ошибка! Флаг %s уже был передан в качестве аргумента коммандной строки, " +
                                "а путь к выходному файлу был проинициализирован!", commandLineArgs[i]));
                        throw new IllegalArgumentException();
                    }
                }
                default -> {
                    log.error(String.format("В командную строку передан невалидный флаг %s. Допустимые флаги: -file и -out. " +
                            "Работа программы остановлена", commandLineArgs[i]));
                    throw new IllegalArgumentException();
                }
            }
        }

        log.debug("Успешное завершение парсинга чтения аргументов командной строки.");
        return configStorage;
    }
}