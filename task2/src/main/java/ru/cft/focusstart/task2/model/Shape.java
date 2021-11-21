package ru.cft.focusstart.task2.model;

import java.text.DecimalFormat;

public abstract class Shape {

    private final String name;
    protected double perimeter;
    protected double area;
    protected static final DecimalFormat dF = new DecimalFormat("#.##");

    protected Shape(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getPerimeter() {
        return perimeter;
    }

    public double getArea() {
        return area;
    }

    public String getTextInfo() {
        return String.format("""
                        Тип фигуры: %s
                        Площадь: %s кв. мм
                        Периметр: %s мм
                        """,
                name,
                dF.format(getArea()),
                dF.format(getPerimeter()));
    }
}