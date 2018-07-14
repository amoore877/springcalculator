package com.easydynamics.calculator;

public class NumberService {
    public static final String ZERO = "0";

    public static String normalize(String numberString) throws IllegalArgumentException {
        if (numberString == null) {
            return ZERO;
        }

        numberString = numberString.trim();
        if (numberString.isEmpty())
            return ZERO;

        if (!numberString.matches("[0-9]+")) {
            throw new IllegalArgumentException("The numberString is not valid");
        }

        return numberString;
    }
}