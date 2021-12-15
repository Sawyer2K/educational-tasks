package ru.cft.focusstart.task4;

public class Task {

    private final long number;
    private final int step;
    private final int startIndex;
    private double result;

    public Task(long number, int step, int startIndex) {
        this.number = number;
        this.step = step;
        this.startIndex = startIndex;
    }

    public void compute() {
        long counter = startIndex;

        while (counter <= number) {
            result += 1. / (counter * (counter + 1.));

            counter += step;
        }
    }

    public double getResult() {
        return result;
    }
}
