package ru.cft.focusstart.task2.engine;

public class ConsoleConfigReader {

    private final String[] commandLineArgs;

    public ConsoleConfigReader(String[] commandLineArgs) {
        this.commandLineArgs = commandLineArgs;
    }

    public AppConfigStorage readCommandLine() {
        var configStorage = new AppConfigStorage();

        if (commandLineArgs.length % 2 != 0) {
            throw new ArrayStoreException("Ошибка! Лист аргументов командной строки содержит нечётное количество элементов!");
        }

        for (int i = 0; i <= commandLineArgs.length - 2; i += 2) {
            switch (commandLineArgs[i]) {
                case "-file" -> configStorage.setPathToInputFile(commandLineArgs[i + 1]);
                case "-out" -> configStorage.setPathToOutputFile(commandLineArgs[i + 1]);
                default -> throw new IllegalArgumentException("В командную строку передан невалидный флаг.");
            }
        }

        return configStorage;
    }
}