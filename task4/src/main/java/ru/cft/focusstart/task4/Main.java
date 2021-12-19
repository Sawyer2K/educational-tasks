package ru.cft.focusstart.task4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Scanner;
import java.util.concurrent.*;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class.getName());

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        System.out.println("Программа вычисляет сходящийся числовой ряд по формуле 1 / (N * (N - 1)). Введите число N.");

        var scanner = new Scanner(System.in);
        var number = scanner.nextLong();

        Runtime runtime = Runtime.getRuntime();
        int threadsCount = runtime.availableProcessors();
        log.info("Количество ядер, видимых JVM равно {}.", threadsCount);

        double result = 0;

        ExecutorService service = Executors.newFixedThreadPool(threadsCount);
        for (var ref = new Object() {
            int i = 1;
        }; ref.i <= threadsCount; ref.i++) {
            Callable<Double> task = () -> new Task(number, threadsCount, ref.i).compute();
            Future<Double> intermediateResult;
            intermediateResult = service.submit(task);
            result += intermediateResult.get();
        }
        service.shutdown();

        System.out.println("Результат вычисления ряда равен " + Math.ceil(result));
    }
}
