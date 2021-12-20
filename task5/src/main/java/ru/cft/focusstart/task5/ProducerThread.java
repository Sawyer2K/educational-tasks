package ru.cft.focusstart.task5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;

public class ProducerThread extends Thread {

    private static final Logger log = LoggerFactory.getLogger(ProducerThread.class.getName());

    private final int id;
    private final ArrayDeque<Resource> storage;
    private final int maxStorageSize;
    private final int producerTime;
    private final Producer producer;

    public ProducerThread(int id, ArrayDeque<Resource> storage, int maxStorageSize, int producerTime) {
        this.id = id;
        this.storage = storage;
        this.maxStorageSize = maxStorageSize;
        this.producerTime = producerTime;
        producer = new Producer(id);
        log.info("Создан производитель № {}", id);
    }

    @Override
    public synchronized void run() {
        while (true) {
            try {
                var secToWait = 1000 * producerTime;
                log.info("Производитель № {} засыпает на {} секунд. Производит ресурс.", id, producerTime);
                Thread.sleep(secToWait);

                producer.produce(storage, maxStorageSize);
            } catch (InterruptedException e) {
                log.warn("Поток {} был разбужен!", Thread.currentThread().getName());
                Thread.currentThread().interrupt();
            }
        }
    }
}
