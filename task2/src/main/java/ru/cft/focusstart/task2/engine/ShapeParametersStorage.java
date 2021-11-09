package ru.cft.focusstart.task2.engine;

import java.util.List;

public class ShapeParametersStorage {

    private String shapeType;
    private List<Double> paramsList;

    public String getShapeType() {
        return shapeType;
    }

    public List<Double> getParamsList() {
        return paramsList;
    }

    public void setShapeType(String shapeType) {
        this.shapeType = shapeType;
    }

    public void setParamsList(List<Double> paramsList) {
        this.paramsList = paramsList;
    }
}
