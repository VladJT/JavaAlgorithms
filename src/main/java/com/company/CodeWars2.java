package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.stream.Collectors;


class CodeWars2 {


    public static String orderWeight(String strng) {
        System.out.println("input->" + strng);

        return Arrays.stream(strng.split("[\s+]")).sorted(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                long n1 = Long.parseLong(o1);
                long n2 = Long.parseLong(o2);
                int sum1 = 0, sum2 = 0;
                while (n1 != 0) {
                    //Суммирование цифр числа
                    sum1 += (n1 % 10);
                    n1 /= 10;
                }
                while (n2 != 0) {
                    //Суммирование цифр числа
                    sum2 += (n2 % 10);
                    n2 /= 10;
                }
                String s1 = String.valueOf(o1);
                String s2 = String.valueOf(o2);

                if (sum1 != sum2) return (sum1 - sum2);
                else
                    return s1.compareTo(s2);
            }
        }).collect(Collectors.joining(" "));


    }

    public static void main(String[] args) {
        System.out.println(orderWeight("3 16 9 38 95 1131268 49455 347464 59544965313 496636983114762 85246814996697"));//2000 103 123 4444 99
        System.out.println(orderWeight("2000 10003 1234000 44444444 9999 11 11 22 123"));//2000 103 123 4444 99
    }


}