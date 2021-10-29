package ru.cft.focusstart.task2.model;

import java.text.DecimalFormat;

public abstract class Shape {

    private final String type;
    protected double perimeter;
    protected double area;

    DecimalFormat dF = new DecimalFormat("#.##");

    Shape(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
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
                        """, type,
                dF.format(getArea()),
                dF.format(getPerimeter()));
    }

    //переименовать в другой метод, не использовать toString
    @Override
    public String toString() {
        return "Shape{" +
                "type='" + type + '\'' +
                ", perimeter=" + perimeter +
                ", area=" + area +
                '}';
    }
}