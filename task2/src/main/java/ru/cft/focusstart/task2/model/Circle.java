package ru.cft.focusstart.task2.model;

public class Circle extends Shape {

    private final double radius;
    private final double diameter;

    public Circle(String type, double radius) {
        super(type);
        this.radius = radius;
        this.diameter = radius * 2;
        calculateParameters();
    }

    public double getRadius() {
        return radius;
    }

    public double getDiameter() {
        return diameter;
    }

    @Override
    public void calculateParameters() {
        perimeter = 2 * Math.PI * radius;
        area = Math.PI * Math.pow(radius, 2);
    }

    @Override
    public String getTextInfo() {
        return super.getTextInfo() + String.format("""
                Радиус: %s мм
                Диаметр: %s мм
                """, dF.format(radius), dF.format(diameter));
    }

    @Override
    public String toString() {
        return "Circle{" +
                "radius=" + radius +
                ", diameter=" + diameter +
                "} " + super.toString();
    }
}