package com.company;


import java.math.BigInteger;
import java.util.*;

class CodeWars2 {

    static Set<String> result = new HashSet<>();
    static char[] mask = {'b', 'a', 'n', 'a', 'n', 'a'};

    static Set<String> bananas(final String s) {
        result.clear();
        findBananas("", s.toCharArray(), 0);
        return result;
    }

    private static void findBananas(String startString, char[] arr, int indexChar) {
        for (int i = startString.length(); i < arr.length; i++) {
            if (arr[i] == mask[indexChar]) {
                if (startString.length() + mask.length - indexChar < arr.length) {
                    findBananas(startString+"-", arr, indexChar); // find another variants
                }
                if (indexChar == 5) result.add(startString+mask[indexChar]+"-".repeat(arr.length-startString.length()-1));
                else {
                    findBananas(startString+mask[indexChar], arr, indexChar + 1);
                }
            }
            startString += '-';
        }//for
    }


    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        //-------------------------

        System.out.println(bananas("bbananana"));
        System.out.println(result.size());

        //-------------------------
        System.out.println("Время выполнения (милисек.): " + (System.currentTimeMillis() - startTime));
    }

}