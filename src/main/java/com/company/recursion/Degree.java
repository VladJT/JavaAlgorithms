package com.company.recursion;

import java.text.DecimalFormat;

/**
 * Написать программу по возведению числа в степень с помощью рекурсии.
 */
public class Degree {

    public static void main(String[] args) {
        DecimalFormat decimalFormat = new DecimalFormat("#.####");
        System.out.println("2^ 8 = " + decimalFormat.format(countDegree(2, 8)));
        System.out.println("2^ -1 = " + decimalFormat.format(countDegree(2, -1)));
        System.out.println("2^ 0 = " + decimalFormat.format(countDegree(2, 0)));
        System.out.println("4^-3 = " + decimalFormat.format(countDegree(4, -3)));
    }


    public static double countDegree(int num, int degree) {
        if (degree == 0) return 1.0;
        return (degree > 0) ? (double) num * countDegree(num, degree - 1) : 1 / (double) num * countDegree(num, degree + 1);
    }
}
