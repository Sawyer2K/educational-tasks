package ru.cft.focusstart.task2.model;

public class Triangle extends Shape {

    private final double sideA;
    private final double sideB;
    private final double sideC;
    private double angleA;
    private double angleB;
    private double angleC;

    public Triangle(String type, double sideA, double sideB, double sideC) {
        super(type);
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
        calculateParameters();
    }

    public double getSideA() {
        return sideA;
    }

    public double getSideB() {
        return sideB;
    }

    public double getSideC() {
        return sideC;
    }

    public double getAngleA() {
        return angleA;
    }

    public double getAngleB() {
        return angleB;
    }

    public double getAngleC() {
        return angleC;
    }

    @Override
    void calculateParameters() {
        perimeter = sideA + sideB + sideC;

        double sideASquare = (Math.pow(sideA, 2));
        double sideBSquare = (Math.pow(sideB, 2));
        double sideCSquare = (Math.pow(sideC, 2));

        angleA = Math.toDegrees(Math.acos((sideBSquare + sideCSquare - sideASquare) / (2 * sideB * sideC)));
        angleB = Math.toDegrees(Math.acos((sideASquare + sideCSquare - sideBSquare) / (2 * sideA * sideC)));
        angleC = Math.toDegrees(Math.acos((sideASquare + sideBSquare - sideCSquare) / (2 * sideA * sideB)));

        area = sideA * sideB * Math.sin(Math.toRadians(angleC)) / 2;
    }

    @Override
    public String getTextInfo() {
        return super.getTextInfo() + String.format("""
                        Сторона A: %s мм, противолежащий угол: %s градусов
                        Сторона B: %s мм, противолежащий угол: %s градусов
                        Сторона C: %s мм, противолежащий угол: %s градусов
                        """, dF.format(sideA),
                dF.format(angleA),
                dF.format(sideB),
                dF.format(angleB),
                dF.format(sideC),
                dF.format(angleC));
    }
}