package ru.cft.focusstart.task5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;

public class Producer {

    private static final Logger log = LoggerFactory.getLogger(Producer.class.getName());

    private final int id;

    public Producer(int id) {
        this.id = id;
    }

    public synchronized void produce(ArrayDeque<Resource> storage, int size) throws InterruptedException {
        log.info("Производитель № {} проснулся и пытается поместить ресурс на склад.", id);

        while (storage.size() == size) {
            log.info("Склад занят, производитель № {} ждёт.", id);
            wait();
        }

        var resource = new Resource();
        storage.add(resource);
        log.info("Производитель № {} поместил ресурс c id {} на склад. Количество ресурсов на складе равно {}",
                id, resource.getId(), storage.size());
        notifyAll();
    }
}
