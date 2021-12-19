package ru.cft.focusstart.task5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Properties;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class.getName());

    private static final String PATH_TO_PROPERTIES = "params.properties";
    volatile static ArrayDeque<Resource> storage = new ArrayDeque<>();
    static int producerCount;
    static int consumerCount;
    static int producerTime;
    static int consumerTime;
    static int maxStorageSize;

    public static void main(String[] args) throws IOException {
        applicationParametersInit();

        log.info("Создан склад, размером {}", maxStorageSize);

        log.info("Создан список потоков-производителей. Количество потоков-производителей {}", producerCount);
        ArrayList<ProducerThread> producers = new ArrayList<>();
        for (var i = 1; i <= producerCount; i++) {
            producers.add(new ProducerThread(i, storage, maxStorageSize, producerTime));
        }

        log.info("Создан список потоков-потребителей. Количество потоков-потребителей {}", producerCount);
        ArrayList<ConsumerThread> consumers = new ArrayList<>();
        for (var i = 1; i <= consumerCount; i++) {
            consumers.add(new ConsumerThread(i, storage, consumerTime));
        }

        for (var producer : producers) {
            producer.start();
        }

        for (var consumer : consumers) {
            consumer.start();
        }
    }

    public static void applicationParametersInit() throws IOException {
        try (InputStream fis = Main.class.getClassLoader().getResourceAsStream(PATH_TO_PROPERTIES)) {
            var properties = new Properties();
            properties.load(fis);

            producerCount = Integer.parseInt(properties.getProperty("PRODUCER_COUNT"));
            consumerCount = Integer.parseInt(properties.getProperty("CONSUMER_COUNT"));
            producerTime = Integer.parseInt(properties.getProperty("PRODUCER_TIME"));
            consumerTime = Integer.parseInt(properties.getProperty("CONSUMER_TIME"));
            maxStorageSize = Integer.parseInt(properties.getProperty("STORAGE_SIZE"));
        } catch (FileNotFoundException e) {
            log.error("Не найден файл params.properties!" + System.lineSeparator() +
                    "Данный файл должен находиться в папке src/main/resource." + System.lineSeparator() +
                    "Внутри даного файла должны находиться параметры программы в виде Ключ = Значение." + System.lineSeparator() +
                    "Например:" + System.lineSeparator() +
                    "PRODUCER_COUNT = 3" + System.lineSeparator() +
                    "CONSUMER_COUNT = 3" + System.lineSeparator() +
                    "PRODUCER_TIME = 5" + System.lineSeparator() +
                    "CONSUMER_TIME = 3" + System.lineSeparator() +
                    "STORAGE_SIZE = 10");
        }
    }
}

