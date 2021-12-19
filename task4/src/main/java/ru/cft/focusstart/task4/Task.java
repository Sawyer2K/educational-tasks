package ru.cft.focusstart.task4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Task {

    private static final Logger log = LoggerFactory.getLogger(Task.class.getName());

    private final long number;
    private final int step;
    private final int startIndex;
    private double result;

    public Task(long number, int step, int startIndex) {
        this.number = number;
        this.step = step;
        this.startIndex = startIndex;
        log.info("Создан Task внутри потока {}", Thread.currentThread().getName());
    }

    public double compute() {
        long counter = startIndex;

        while (counter <= number) {
            result += 1. / (counter * (counter + 1.));

            counter += step;
        }

        log.info("Промежуточный результат Task внутри потока {} равен {}.", Thread.currentThread().getName(), result);

        return result;
    }
}
