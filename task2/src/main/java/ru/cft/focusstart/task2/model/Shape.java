package ru.cft.focusstart.task2.model;

import java.text.DecimalFormat;

public abstract class Shape {

    private final String name;
    protected double perimeter;
    protected double area;

    DecimalFormat dF = new DecimalFormat("#.##");

    Shape(String name) {
        this.name = name;
    }

    public double getPerimeter() {
        return perimeter;
    }

    public double getArea() {
        return area;
    }

    abstract void calculateParameters();

    public String getTextInfo() {
        return String.format("""
                        Тип фигуры: %s
                        Площадь: %s кв. мм
                        Периметр: %s мм
                        """, name,
                dF.format(getArea()),
                dF.format(getPerimeter()));
    }
}