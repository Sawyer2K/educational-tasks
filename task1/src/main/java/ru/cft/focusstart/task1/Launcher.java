package ru.cft.focusstart.task1;

import java.io.PrintWriter;
import java.util.Scanner;

public class Launcher {

    public static final Scanner SCANNER = new Scanner(System.in);
    public static final int MAX_VALUE = 32;

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        System.out.printf("Введите размерность таблицы умножения. Допустимы размерности от 1 до %d включительно. %s" +
                        "Ввод 0 приведёт к закрытию программы%s",
                MAX_VALUE, System.lineSeparator(), System.lineSeparator());

        var input = processUserInput();

        if (input == 0) {
            System.out.println("Программа завершена корректно");
            return;
        }

        Matrix matrix = new MultiplicationMatrix(input);
        Drawer drawer = new Drawer();

        try (PrintWriter pw = new PrintWriter(System.out)) {
            pw.println(drawer.getAsText(matrix));
        }
    }

    private static int processUserInput() {
        do {
            try {
                var input = SCANNER.next();
                var result = Integer.parseInt(input);

                if (result >= 0 && result <= MAX_VALUE) {
                    return result;
                }

                System.out.printf("Введённое число должно быть в промежутке от 0 до %d включительно. Повторите ввод.%s",
                        MAX_VALUE, System.lineSeparator());
            } catch (NumberFormatException e) {
                System.out.printf("Входные данные некорректны. Введите число в промежутке от 0 до %d включительно. Повторите ввод.%s",
                        MAX_VALUE, System.lineSeparator());
            }
        } while (SCANNER.hasNext());

        return 0;
    }
}
