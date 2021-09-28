package com.API;

public abstract class DeviceSpecs {
    private String name;
    private double default_value;
    private double max;
    private double min;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDefault_value(double default_value) {
        this.default_value = default_value;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getDefault_value() {
        return default_value;
    }

}
