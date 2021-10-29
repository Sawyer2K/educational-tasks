package ru.cft.focusstart.task2.model;

public class Rectangle extends Shape {

    private final double length;
    private final double width;
    protected double diagonal;

    public Rectangle(String type, double length, double width) {
        super(type);
        this.length = Math.max(length, width);
        this.width = Math.min(length, width);
        calculateParameters();
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public double getDiagonal() {
        return diagonal;
    }

    @Override
    void calculateParameters() {
        perimeter = length * 2 + width * 2;
        area = length * width;
        diagonal = Math.sqrt(Math.pow(length, 2) + Math.pow(width, 2));
    }

    @Override
    public String getTextInfo() {
        return super.getTextInfo() + String.format("""
                        Длина диагонали: %s мм
                        Длина: %s мм
                        Ширина: %s мм
                        """, dF.format(diagonal),
                dF.format(length),
                dF.format(width));
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "length=" + length +
                ", width=" + width +
                ", diagonal=" + diagonal +
                "} " + super.toString();
    }
}