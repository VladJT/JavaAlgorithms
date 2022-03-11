package com.company;


import java.util.*;
import java.util.stream.Collectors;

class CodeWars extends Object {


    static Set<String> bananas(final String s) {
        Set<String> stSet = new HashSet<>();
        if (s.equals("banana")) {
            stSet.add(s);
            return stSet;
        }

        String tempS = s.substring(0, s.length() - 5);

        int bIndex = tempS.indexOf('b');


        int[] arr = new int[s.length() - 6];
        int[] maxArr = new int[s.length() - 6];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = bIndex + i;
            maxArr[i] = i + 6;

        }

        while (!Arrays.equals(arr, maxArr)) {
            checkBanana(s, stSet, arr);

            boolean exit = false;
            do {
                for (int i = 0; i < arr.length; i++) {
                    if (arr[i] < maxArr[i]) {
                        arr[i]++;
                        break;
                    } else {
                        arr[i] = i;
                    }
                }
                if (Arrays.equals(arr, maxArr)) exit = true;


                int firstN = 0;

                while (true) {
                    int finalFirstN = firstN;
                    if (Arrays.stream(arr).filter(n -> n == finalFirstN).count() == 0) break;
                    firstN++;
                }
                if (s.charAt(firstN) == 'b') exit = true;

            } while (exit == false);

            // последняя итерация
            if (Arrays.equals(arr, maxArr)) {
                checkBanana(s, stSet, maxArr);
            }
        }

        return stSet.isEmpty() ? Collections.EMPTY_SET : stSet;
    }

    private static void checkBanana(String s, Set<String> stSet, int[] arr) {
        String rez = getBananaString(s, arr);
        if (rez.replaceAll("-", "").equals("banana")) {
            stSet.add(rez);
        }
    }

    private static String getBananaString(String s, int[] arr) {
        char[] c = s.toCharArray();
        for (int j : arr) c[j] = '-';
        return String.valueOf(c);
    }

    public static void main(String[] args) {
        String input = "abananaaa";
        Set<String> expected = bananas(input);
        for (String s : expected) {
            System.out.println(s);
        }
    }


}


