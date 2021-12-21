package ru.cft.focusstart.task4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyThread extends Thread {

    private static final Logger log = LoggerFactory.getLogger(MyThread.class.getName());

    private final int step;
    private final long number;
    private final int startIndex;
    private Task task;

    public MyThread(long number, int step, int startIndex) {
        this.step = step;
        this.number = number;
        this.startIndex = startIndex;
        log.info("Создан экземпляр {}.", this.getName());
    }

    @Override
    public void run() {
        log.info("Создание экземпляра Task внутри {}.", this.getName());

        task = new Task(number, step, startIndex);
        task.compute();

        log.info("Task внутри {} окончил работу.", this.getName());
    }

    public Task getTask() {
        return task;
    }
}
