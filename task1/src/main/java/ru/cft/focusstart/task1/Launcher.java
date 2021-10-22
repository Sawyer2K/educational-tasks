package ru.cft.focusstart.task1;

import java.util.Scanner;

public class Launcher {

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        MatrixGenerator matrixGenerator;
        Drawer drawer;

        System.out.println("Введите размерность таблицы умножения. Допустимы размерности от 1 до 32 включительно.\n" +
                "Ввод 0 приведёт к закрытию программы");

        var input = processUserInput();

        if (input == 0) {
            System.out.println("Программа завершена корректно");
            System.exit(0);
        }

        matrixGenerator = new MatrixGeneratorImpl(input);
        drawer = new Drawer();

        System.out.println(drawer.outputTheTable(matrixGenerator));
    }

    /*Метод создает объект сканнера внутри себя.
    Внутри метода производится проверка валидности введённых данных - является ли строка числовым значением или нет.
    Так же проверяется соответствие введённого значения допустимому диапазону.
    В обоих случаях выводится информационное сообщение, работа программы не останавливется - пользователю предлагается
    повторный ввод.*/
    private static int processUserInput() {
        var scanner = new Scanner(System.in);

        while (true) {
            var input = scanner.next();

            try {
                var result = Integer.parseInt(input);

                if (result >= 0 & result <= 32) {
                    return result;
                } else {
                    System.out.println("Введённое число должно быть в промежутке от 0 до 32 включительно.\n" +
                            "Повторите ввод.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Входные данные некорректны. Введите число в промежутке от 0 до 32 включительно.\n" +
                        "Повторите ввод.");
            }
        }
    }
}
