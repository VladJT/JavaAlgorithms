package com.company.core;

public class l2_BasicElements {

    static int x = 1;

    public static void main(String[] args) {
        // 1
        System.out.println("11 + 20 in 10..20 ? " + checkSum(11, 20));

        // 2
        checkNumber(-5);
        checkNumber(6);

        // 3
        System.out.println(" -1 отрицательное ? " + isNegative(-1));

        // 4
        printString("🍮🍯🍵🫖🍬🍫", 5);

        //5
        checkLeepYear(2000);
        checkLeepYear(2024);
        checkLeepYear(2100);
        checkLeepYear(2023);
    }


    public static boolean checkSum(int a, int b) {
        return ((a + b) >= 10 && (a + b) <= 20);
    }

    public static void checkNumber(int a) {
        System.out.printf(a >= 0 ? "%d положительное%n" : "%d отрицательное%n", a);
    }

    public static boolean isNegative(int a) {
        return (a < 0 ? true : false);
    }

    public static void printString(String st, int num) {
        for (int i = 0; i < num; i++) {
            System.out.println(st);
        }
    }

    public static boolean isLeepYear(int year) {
        if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0))
            return true;
        else return false;
    }

    public static void checkLeepYear(int year) {
        if (isLeepYear(year)) System.out.println(year + " високосный");
        else System.out.println(year + " не високосный");
    }

}
