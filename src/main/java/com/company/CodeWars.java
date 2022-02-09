package com.company;

import java.util.*;
import java.util.stream.IntStream;


public class CodeWars {
    /**
     * "3x^2"  => "6x"
     * "-5x^3" => "-15x^2"
     * "6x^-2" => "-12x^-3"
     * "5x"    => "5"
     * "-x"    => "-1"
     * "42"    => "0"
     */
    public static String differentiate(String function) {
        String delimiter = "x";
        String[] st = function.split(delimiter);

        //"42"    => "0"
        if(st.length==1) return "0";

        String left = st[0];
        String right = st[1];

        //  * "-x"    => "-1"
        if (left.equals("-")) left = "-1";
        if(right.equals("")) return left;

        //* "6x^-2" => "-12x^-3"
        right = right.substring(1, right.length());
        int l = Integer.parseInt(left);
        int r = Integer.parseInt(right);
        l *= r;
        r -= 1;
        return l+"x"+(char)94+r;


    }


    public static void main(String[] args) {
        System.out.println(differentiate("6x^-2"));

    }
}
