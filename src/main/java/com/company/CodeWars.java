package com.company;


import java.util.*;

class CodeWars extends Object {


    static Set<String> bananas(final String s) {

        char[] f = "banana".toCharArray();

        int[][] variants = new int[8][];

        for (int i = 0; i < f.length; i++) {
            int n = 0;

            for (int j = 0; j < s.length(); j++) {
                if (f[i] == s.charAt(j)) {
                    n++;
                }
            }

            variants[i] = new int[n];
            n = 0;
            for (int j = 0; j < s.length(); j++) {
                if (f[i] == s.charAt(j)) {
                    variants[i][n++] = j;
                }
            }

        }//for i

        Set<String> stSet = new HashSet<>();

        for (int i = 1; i < f.length; i++) {



        }


        return Collections.EMPTY_SET;
    }

    public static void main(String[] args) {
        String input = "bananaaa";
        Set<String> expected = bananas(input);
        for (String s : expected) {
            System.out.println(s);
        }
    }


}


