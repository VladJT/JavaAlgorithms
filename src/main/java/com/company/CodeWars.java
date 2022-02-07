package com.company;

import java.util.*;


public class CodeWars {

    public static String printerError(String s) {
        return s.replaceAll("[a-m]", "");

    }


    public static void main(String[] args) {

        String s="aaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbmmmmmmmmmmmmmmmmmmmxyz";
        System.out.println(printerError(s));

    }
}
