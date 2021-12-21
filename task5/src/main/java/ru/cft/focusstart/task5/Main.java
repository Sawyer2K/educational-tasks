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

        ArrayList<ProducerThread> producers = new ArrayList<>();
        log.info("Создан склад, размером {}", maxStorageSize);

        for (var i = 1; i <= producerCount; i++) {
            producers.add(new ProducerThread(i, storage, maxStorageSize, producerTime));
        }
        log.info("Создан список потоков-производителей. Количество потоков-производителей {}", producerCount);

        ArrayList<ConsumerThread> consumers = new ArrayList<>();
        for (var i = 1; i <= consumerCount; i++) {
            consumers.add(new ConsumerThread(i, storage, consumerTime));
        }
        log.info("Создан список потоков-потребителей. Количество потоков-потребителей {}", producerCount);

        for (var producer : producers) {
            producer.start();
        }

        for (var consumer : consumers) {
            consumer.start();
        }
    }

    public static void applicationParametersInit() throws IOException {
        log.info("Началась инициализация параметров программы." + System.lineSeparator() +
                "Значение параметров указывается в конфигурационном файле param.properties и должно быть положительным и не нулевым." + System.lineSeparator() +
                "При указании невалидных значений, параметры будут проинициализированы значением по умолчанию, равным 1." + System.lineSeparator());

        try (InputStream fis = Main.class.getClassLoader().getResourceAsStream(PATH_TO_PROPERTIES)) {
            var properties = new Properties();
            properties.load(fis);

            producerCount = Math.max(1, Integer.parseInt(properties.getProperty("PRODUCER_COUNT")));
            consumerCount = Math.max(1, Integer.parseInt(properties.getProperty("CONSUMER_COUNT")));
            producerTime = Math.max(1, Integer.parseInt(properties.getProperty("PRODUCER_TIME")));
            consumerTime = Math.max(1, Integer.parseInt(properties.getProperty("CONSUMER_TIME")));
            maxStorageSize = Math.max(1, Integer.parseInt(properties.getProperty("STORAGE_SIZE")));
        } catch (FileNotFoundException e) {
            log.error("Не найден файл params.properties!" + System.lineSeparator() +
                    "Данный файл должен находиться в папке src/main/resource." + System.lineSeparator() +
                    "Внутри даного файла должны находиться параметры программы в виде Ключ = Значение." + System.lineSeparator() +
                    "Например:" + System.lineSeparator() +
                    "PRODUCER_COUNT = 3" + System.lineSeparator() +
                    "CONSUMER_COUNT = 3" + System.lineSeparator() +
                    "PRODUCER_TIME = 5" + System.lineSeparator() +
                    "CONSUMER_TIME = 3" + System.lineSeparator() +
                    "STORAGE_SIZE = 10" + System.lineSeparator() +
                    "При указании отрицательного либо нулевого значения, параметру будет установлено значение по умолчанию равное 1.");
        }
    }
}

