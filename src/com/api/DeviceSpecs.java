package com.api;

public class DeviceSpecs {
    protected transient String name;
    private double defaultValue;
    private double max;
    private double min;

    public DeviceSpecs(final String name) {
        this.name = name;
    }

    public DeviceSpecs() {
    }

    /**
     * Gets specifications name specific to the component
     *
     * @return name as a String
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the default value of a component
     *
     * @return defaultValue as a double
     */
    public double getDefaultValue() {
        return defaultValue;
    }

    /**
     * Sets the default value of a component
     *
     * @param defaultValue as a double
     */
    public void setDefaultValue(final double defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * Gets maximum value of a component
     *
     * @return max as a double
     */
    public double getMax() {
        return max;
    }

    /**
     * Sets the maximum value of a component
     *
     * @param max as a double
     */
    public void setMax(final double max) {
        this.max = max;
    }

    /**
     * Gets minimum value of a component
     *
     * @return min as a double
     */
    public double getMin() {
        return min;
    }

    /**
     * Sets the minimum value of a component
     *
     * @param min as a double
     */
    public void setMin(final double min) {
        this.min = min;
    }


}
