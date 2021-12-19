package ru.cft.focusstart.task5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;

public class ConsumerThread extends Thread {

    private static final Logger log = LoggerFactory.getLogger(ConsumerThread.class.getName());

    private final int id;
    private final int consumerTime;
    private final ArrayDeque<Resource> storage;
    private final Consumer consumer;

    public ConsumerThread(int id, ArrayDeque<Resource> storage, int consumerTime) {
        this.id = id;
        this.storage = storage;
        this.consumerTime = consumerTime;
        consumer = new Consumer(id);
        log.info("Создан потребитель № {}", id);
    }

    @Override
    public synchronized void run() {
        while (true) {
            try {
                var secToWait = 1000 * consumerTime;
                log.info("Потребитель № {} засыпает на {} секунд. Производит ресурс.", id, consumerTime);
                Thread.sleep(secToWait);

                consumer.consume(storage);
            } catch (InterruptedException e) {
                log.warn("Поток {} был разбужен!", Thread.currentThread().getName());
            }
        }
    }
}
