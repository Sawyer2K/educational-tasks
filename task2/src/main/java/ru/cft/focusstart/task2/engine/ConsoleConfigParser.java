package ru.cft.focusstart.task2.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsoleConfigParser {

    private static final Logger log = LoggerFactory.getLogger(ConsoleConfigParser.class.getName());

    private final String[] commandLineArgs;

    public ConsoleConfigParser(String[] commandLineArgs) {
        this.commandLineArgs = commandLineArgs;
    }

    public AppConfigStorage parseCommandLine() {
        log.debug("Начало чтения аргументов командной строки.");

        var configStorage = new AppConfigStorage();

        if (commandLineArgs.length % 2 != 0) {
            log.error("Ошибка! Список аргументов командной строки содержит нечётное количество элементов! " +
                    "Работа программы остановлена.");
            throw new IllegalArgumentException();
        }

        for (int i = 0; i <= commandLineArgs.length - 2; i += 2) {
            switch (commandLineArgs[i]) {
                case "-file" -> configStorage.setPathToInputFile(commandLineArgs[i + 1]);
                case "-out" -> configStorage.setPathToOutputFile(commandLineArgs[i + 1]);
                default -> {
                    log.error(String.format("В командную строку передан невалидный флаг %s. Допустимые флаги: -file и -out. " +
                            "Работа программы остановлена", commandLineArgs[i]));
                    throw new IllegalArgumentException();
                }
            }
        }

        log.debug("Успешное окончание чтения аргументов командной строки.");
        return configStorage;
    }
}