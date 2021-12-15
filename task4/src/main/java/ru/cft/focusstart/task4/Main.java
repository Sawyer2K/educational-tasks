package ru.cft.focusstart.task4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class.getName());

    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        var number = scanner.nextLong();

        Runtime runtime = Runtime.getRuntime();
        int threadsCount = runtime.availableProcessors();
        log.info("Количество ядер, видимых JVM равно {}.", threadsCount);

        var threadsList = new ArrayList<MyThread>();
        for (var index = 1; index <= threadsCount; index++) {
            var thread = new MyThread(number, threadsCount, index);
            threadsList.add(thread);

            thread.start();
            log.info("Тред {} запущен.", thread.getName());
        }

        for (var thread : threadsList) {
            try {
                thread.join();
                log.info("Тред {} выполнил вычисления и перешёл в режим ожидания. Приблизительный промежуточный результат равен {}",
                        thread.getName(), String.format("%.5f", thread.getTask().getResult()));
            } catch (InterruptedException e) {
                log.warn("Поток {} был прерван.", thread.getName());
                e.printStackTrace();
            }
        }
        log.info("Все треды выполнили вычисления и перешли в режим ожидания.");

        double result = 0;
        for (var thread : threadsList) {
            result = result + thread.getTask().getResult();
        }
        log.info("Приблизительная сумма результатов вычислений всех тредов равна {}", String.format("%.5f", result));

        System.out.println(Math.round(result));
    }
}
