package ru.cft.focusstart.task1;

import java.util.Scanner;

public class Launcher {

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        DataHolder dataHolder;
        Drawer drawer;

        System.out.println("Введите размерность таблицы умножения. Допустимы размерности от 1 до 32.\n" +
                "Ввод 0 приведёт к закрытию программы");

        int input = processUserInput();

        if (input == 0) {
            System.out.println("Программа завершена коректно");
            System.exit(0);
        }

        dataHolder = new DataHolder(input);
        drawer = new Drawer(input);

        drawer.outputTheTable(dataHolder);
    }

    public static int processUserInput() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.next();

            if (input.matches("\\d+")) {
                return Integer.parseInt(input);
            } else {
                System.out.println("Входные данные некорректны. Повторите ввод.");
            }
        }
    }
}
