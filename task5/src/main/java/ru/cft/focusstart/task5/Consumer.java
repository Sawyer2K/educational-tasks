package ru.cft.focusstart.task5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;

public class Consumer {

    private static final Logger log = LoggerFactory.getLogger(Consumer.class.getName());

    private final int id;

    public Consumer(int id) {
        this.id = id;
    }

    public synchronized void consume(ArrayDeque<Resource> storage) throws InterruptedException {
        log.info("Потребитель № {} проснулся и пытается потребить ресурс из склада.", id);

        while (storage.size() == 0) {
            log.info("Склад пуст, потребитель № {} ждёт.", id);
            wait();
        }

        var resource = storage.pop();
        log.info("Потребитель № {} потребил ресурс с id {} со склада. Количество ресурсов на складе равно {}",
                id, resource.getId(), storage.size());
        notifyAll();
    }
}
