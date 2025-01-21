package org.example.lib;

public class ValueValidator {

    public void validateNotZero(double value, String name) {
        if (value == 0) {
            throw new IllegalArgumentException(name + " cannot be zero");
        }
    }

    public void validateNumeric(double value, String name) {
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException(name + " must be a numeric value");
        }
    }
}
