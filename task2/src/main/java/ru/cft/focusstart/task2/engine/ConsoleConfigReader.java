package ru.cft.focusstart.task2.engine;

public class ConsoleConfigReader {
    String[] commandLineArgs;

    public ConsoleConfigReader(String[] commandLineArgs) {
        this.commandLineArgs = commandLineArgs;
    }

    public ApplicationConfigStorage readInput() {
        var configStorage = new ApplicationConfigStorage();

        if (commandLineArgs.length % 2 != 0) {
            throw new ArrayStoreException("The List must contain an even number of arguments");
        }

        for (int i = 0; i <= commandLineArgs.length - 2; i += 2) {
            switch (commandLineArgs[i]) {
                case "-file":
                    configStorage.setPathToInputFile(commandLineArgs[i + 1]);
                    break;
                case "-out":
                    configStorage.setPathToOutputFile(commandLineArgs[i + 1]);
                    break;
                default:
                    throw new IllegalArgumentException("Wrong flag");
            }
        }

        return configStorage;
    }
}