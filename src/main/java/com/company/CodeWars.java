package com.company;


import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class CodeWars extends Object {


    public static boolean isDivisible(int... arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[0] % arr[i] != 0) return false;
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println(isDivisible(3, 3, 4));//  10011010010, so the function should return 5 in


    }


}


