package com.company;

import java.util.*;
import java.util.stream.IntStream;


public class CodeWars {


    public static double[] tribonacci(double[] s, int n) {
        double[] trib = new double[0];
        if (n == 0) return trib;
        trib = new double[n];

        for (int i = 0; i < n; i++) {
            if (i < 3) trib[i] = s[i];
            else trib[i] = trib[i - 1] + trib[i - 2] + trib[i - 3];
        }
        return trib;
    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(tribonacci(new double[]{5.0, 14.0, 17.0} , 0)));

    }
}
