package ru.cft.focusstart.task6.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Properties;

public class ServerConfigurator {

    private static final Logger log = LoggerFactory.getLogger(ServerConfigurator.class.getName());

    private final int port;

    public ServerConfigurator() throws Exception {
        this.port = readPortFromConfigFile();
    }

    public int getPort() {
        return port;
    }

    private int readPortFromConfigFile() throws Exception {
        Properties properties = new Properties();
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("server.properties")).getFile());

        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            properties.load(fileInputStream);
        } catch (FileNotFoundException e) {
            log.error("Не удалось найти файл server.properties");
            throw new FileNotFoundException();
        } catch (Exception e) {
            log.error("Не удалось прочитать файл!");
            throw new Exception();
        }

        try {
            int port = Integer.parseInt(properties.getProperty("PORT"));

            if (port < 0 | port >= Integer.MAX_VALUE) {
                log.info("В конфигурационном файле значение PORT имеет недопустимое значение (меньше нуля или больше 65535).");
                throw new Exception("Не удалось получить порт");
            }

            return port;

        } catch (NumberFormatException e) {
            log.info("В конфигурационном файле обнаружен неверный формат данных для ключа PORT");
            throw new Exception("Не удалось получить порт");
        }
    }
}