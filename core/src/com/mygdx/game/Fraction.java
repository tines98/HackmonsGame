package com.mygdx.game;

public class Fraction {

    private int numerator;
    private int denominator;

    public Fraction () {
        numerator = 2;
        denominator = 2;
    }

    public Fraction(int numr, int denr) {
        numerator = numr;
        denominator = denr;
    }

    public double getDecimal() {
        return numerator / denominator;
    }

    public void increase(int stages) {
        if (denominator != 2) {
            denominator--;
        }
        else {
            if (numerator != 8) {
                numerator++;
            }
        }
        if (stages > 1) {
            increase(stages-1);
        }
    }
    public void decrease(int stages) {
        if (numerator != 2) {
            numerator--;
        }
        else {
            if (denominator != 8) {
                denominator--;
            }
        }
        if (stages > 1) {
            decrease(stages-1);
        }
    }

    public void reset() {
        numerator = 2;
        denominator = 2;
    }
}
