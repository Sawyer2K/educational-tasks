package ru.cft.focusstart.task1;

import java.util.Scanner;

public class Launcher {

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        MatrixGenerator matrixGeneratorImpl;
        Drawer drawer;

        System.out.println("Введите размерность таблицы умножения. Допустимы размерности от 1 до 32 включительно.\n" +
                "Ввод 0 приведёт к закрытию программы");

        int input = processUserInput();

        if (input == 0) {
            System.out.println("Программа завершена корректно");
            System.exit(0);
        }

        matrixGeneratorImpl = new MatrixGeneratorImpl(input);
        drawer = new Drawer();

        drawer.outputTheTable(matrixGeneratorImpl);
    }

    public static int processUserInput() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.next();

            if (input.matches("\\d+")) {
                int result = Integer.parseInt(input);

                if (result <= 32) {
                    return result;
                } else {
                    System.out.println("Введённое число должно быть в промежутке от 0 до 32 включительно.\n" +
                            "Повторите ввод.");
                }
            } else {
                System.out.println("Входные данные некорректны. Повторите ввод.");
            }
        }
    }
}
