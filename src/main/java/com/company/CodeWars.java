package com.company;

import java.util.*;
import java.util.stream.IntStream;


public class CodeWars {


    private static int[] ints;

    public static boolean comp(int[] a, int[] b) {
        if (a == null || b == null) return false;
        ints = Arrays.stream(a).map(n -> n * n).toArray();
        Arrays.sort(ints);
        Arrays.sort(b);
        return (Arrays.equals(ints, b));
    }


    public static void main(String[] args) {
        int[] a = new int[]{121, 144, 19, 161, 19, 144, 19, 11};
        int[] b = new int[]{121, 14641, 20736, 361, 25921, 361, 20736, 361};
        System.out.println(comp(a, b));

    }
}
